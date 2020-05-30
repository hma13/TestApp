package com.example.github.ui.commit;

import androidx.core.util.Pair;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.github.db.CommitListItemEntity;
import com.example.github.repository.CommitRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class CommitListFragmentViewModel extends ViewModel {
    private CommitRepository commitRepository;
    private MutableLiveData<Boolean> fetchingLiveData = new MutableLiveData<>();
    private MutableLiveData<Pair<List<CommitListItemEntity>, Throwable>> commitsLiveData = new MutableLiveData<>();
    private Disposable disposable;

    @Inject
    CommitListFragmentViewModel(CommitRepository commitRepository) {
        this.commitRepository = commitRepository;
    }

    void fetchCommits(boolean force) {
        if (disposable != null) {
            disposable.dispose();
        }
        Flowable<List<CommitListItemEntity>> commitsFlowable = commitRepository.getCommits("hma13", "TestApp", null);
        if (force) {
            commitsFlowable = commitRepository.getRemoteCommits("hma13", "TestApp", null);
        }
        //passes null to branchName for 'master' branch
        disposable = commitsFlowable
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> fetchingLiveData.setValue(true))
                .doOnNext((list) -> fetchingLiveData.setValue(false))
                .subscribe(commitListItemEntities -> {
                    Timber.d("size: %d", commitListItemEntities.size());
                    commitsLiveData.setValue(Pair.create(commitListItemEntities, null));
                }, throwable -> {
                    Timber.e(throwable);
                    commitsLiveData.setValue(Pair.create(null, throwable));
                });

    }

    @Override
    protected void onCleared() {
        if (disposable != null) {
            disposable.dispose();
        }
        super.onCleared();
    }

    LiveData<Boolean> getFetchingLiveData() {
        return fetchingLiveData;
    }

    LiveData<Pair<List<CommitListItemEntity>, Throwable>> getCommitsLiveData() {
        return commitsLiveData;
    }

    public void update(String sha) {
        commitRepository.update(sha).subscribe(() -> {
            Timber.d("updated");
        });
    }
}
