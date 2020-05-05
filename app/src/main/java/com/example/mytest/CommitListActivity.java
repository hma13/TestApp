package com.example.mytest;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mytest.repo.DataRepo;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CommitListActivity extends AppCompatActivity {
    @Inject
    DataRepo dataRepo;
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        compositeDisposable = new CompositeDisposable();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commit_list);

        compositeDisposable.add(dataRepo.getCommits("hma13", "TestApp").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((commits, throwable) -> {
        }));
    }


    @Override
    protected void onStop() {
        compositeDisposable.dispose();
        super.onStop();
    }
}
