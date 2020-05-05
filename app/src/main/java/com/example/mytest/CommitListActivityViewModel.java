package com.example.mytest;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mytest.data.Commit;
import com.example.mytest.repo.DataRepo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class CommitListActivityViewModel extends ViewModel {
    @Inject
    DataRepo dataRepo;

    private MutableLiveData<Boolean> fetchingLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Commit>> commitsLiveData = new MutableLiveData<>();
    private Disposable disposable;

    @Inject
    public CommitListActivityViewModel(DataRepo dataRepo) {
        this.dataRepo = dataRepo;
    }

    void fetchCommits() {
        //passes null to branchName for 'master'
        disposable = dataRepo.getCommits("hma13", "TestApp", null)
                .doOnSubscribe(disposable -> fetchingLiveData.postValue(true))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> fetchingLiveData.setValue(false))
                .subscribe((commits, throwable) -> {
                    if (throwable != null) {
                        Timber.e(throwable);
                    } else {
                        Timber.d("size: %d", commits.size());
                        commitsLiveData.setValue(commits);
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

    public MutableLiveData<Boolean> getFetchingLiveData() {
        return fetchingLiveData;
    }

    public MutableLiveData<List<Commit>> getCommitsLiveData() {
        return commitsLiveData;
    }
}
