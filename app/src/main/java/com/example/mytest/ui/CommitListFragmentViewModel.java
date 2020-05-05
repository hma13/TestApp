package com.example.mytest.ui;

import androidx.core.util.Pair;
import androidx.lifecycle.LiveData;
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

public class CommitListFragmentViewModel extends ViewModel {
    @Inject
    DataRepo dataRepo;

    private MutableLiveData<Boolean> fetchingLiveData = new MutableLiveData<>();
    private MutableLiveData<Pair<List<Commit>, Throwable>> commitsLiveData = new MutableLiveData<>();
    private Disposable disposable;

    @Inject
    public CommitListFragmentViewModel(DataRepo dataRepo) {
        this.dataRepo = dataRepo;
    }

    void fetchCommits() {
        //passes null to branchName for 'master' branch
        disposable = dataRepo.getCommits("hma13", "TestApp", null)
                .doOnSubscribe(disposable -> fetchingLiveData.postValue(true))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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

    LiveData<Pair<List<Commit>, Throwable>> getCommitsLiveData() {
        return commitsLiveData;
    }
}
