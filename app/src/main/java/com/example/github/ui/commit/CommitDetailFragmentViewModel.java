package com.example.github.ui.commit;

import androidx.core.util.Pair;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.github.data.CommitDetail;
import com.example.github.repository.CommitRepository;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class CommitDetailFragmentViewModel extends ViewModel {
    private MutableLiveData<Boolean> fetchingLiveData = new MutableLiveData<>();
    private MutableLiveData<Pair<CommitDetail, Throwable>> commitLiveData = new MutableLiveData<>();
    private Disposable disposable;
    private CommitRepository commitRepository;

    @Inject
    public CommitDetailFragmentViewModel(CommitRepository commitRepository) {
        this.commitRepository = commitRepository;
    }

    void fetchCommit(String hash) {
        //passes null to branchName for 'master' branch
        disposable = commitRepository.getCommit("hma13", "TestApp", hash)
                .doOnSubscribe(disposable -> fetchingLiveData.postValue(true))
                .doAfterTerminate(() -> fetchingLiveData.setValue(false))
                .subscribe((commit, throwable) -> {
                    if (throwable != null) {
                        Timber.e(throwable);
                        commitLiveData.setValue(Pair.create(null, throwable));
                    } else {
                        commitLiveData.setValue(Pair.create(commit, null));
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

    LiveData<Pair<CommitDetail, Throwable>> getCommitLiveData() {
        return commitLiveData;
    }
}
