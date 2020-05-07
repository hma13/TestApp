package com.example.github.ui.commit;

import androidx.core.util.Pair;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.github.data.Commit;
import com.example.github.repo.DataRepo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class CommitDetailFragmentViewModel extends ViewModel {
    @Inject
    DataRepo dataRepo;

    private MutableLiveData<Boolean> fetchingLiveData = new MutableLiveData<>();
    private MutableLiveData<Pair<List<Commit>, Throwable>> commitLiveData = new MutableLiveData<>();
    private Disposable disposable;

    @Inject
    public CommitDetailFragmentViewModel(DataRepo dataRepo) {
        this.dataRepo = dataRepo;
    }

    void fetchCommit() {
        //passes null to branchName for 'master' branch
        disposable = dataRepo.getCommits("hma13", "TestApp", null)
                .doOnSubscribe(disposable -> fetchingLiveData.postValue(true))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> fetchingLiveData.setValue(false))
                .subscribe((commits, throwable) -> {
                    if (throwable != null) {
                        Timber.e(throwable);
                        commitLiveData.setValue(Pair.create(null, throwable));
                    } else {
                        Timber.d("size: %d", commits.size());
                        commitLiveData.setValue(Pair.create(commits, null));
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

    LiveData<Pair<List<Commit>, Throwable>> getCommitLiveData() {
        return commitLiveData;
    }
}
