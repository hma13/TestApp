package com.example.github.ui.commit;

import androidx.core.util.Pair;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.github.data.CommitListItem;
import com.example.github.repository.CommitRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class CommitListFragmentViewModel extends ViewModel {
    private CommitRepository commitRepository;
    private MutableLiveData<Boolean> fetchingLiveData = new MutableLiveData<>();
    private MutableLiveData<Pair<List<CommitListItem>, Throwable>> commitsLiveData = new MutableLiveData<>();
    private Disposable disposable;

    @Inject
    public CommitListFragmentViewModel(CommitRepository commitRepository) {
        this.commitRepository = commitRepository;
    }

    void fetchCommits() {
        //passes null to branchName for 'master' branch
        disposable = commitRepository.getCommits("hma13", "TestApp", null)
                .doOnSubscribe(disposable -> fetchingLiveData.postValue(true))
                .doAfterTerminate(() -> fetchingLiveData.setValue(false))
                .subscribe((commits, throwable) -> {
                    if (throwable != null) {
                        Timber.e(throwable);
                        commitsLiveData.setValue(Pair.create(null, throwable));
                    } else {
                        Timber.d("size: %d", commits.size());
                        commitsLiveData.setValue(Pair.create(commits, null));
                    }
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

    LiveData<Pair<List<CommitListItem>, Throwable>> getCommitsLiveData() {
        return commitsLiveData;
    }
}
