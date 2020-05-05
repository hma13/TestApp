package com.example.mytest;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mytest.databinding.ActivityCommitListBinding;
import com.example.mytest.repo.DataRepo;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class CommitListActivity extends AppCompatActivity {
    @Inject
    DataRepo dataRepo;
    private CompositeDisposable compositeDisposable;
    private ActivityCommitListBinding binding;
    private CommitListAdaptor commitListAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        compositeDisposable = new CompositeDisposable();
        super.onCreate(savedInstanceState);
        binding = ActivityCommitListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        commitListAdaptor = new CommitListAdaptor(this);
        binding.commitList.setAdapter(commitListAdaptor);
        compositeDisposable.add(dataRepo.getCommits("hma13", "TestApp", "develop").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((commits, throwable) -> {
            if (throwable != null) {
                Timber.e(throwable);
            }
            Timber.d("size: %d", commits.size());
            commitListAdaptor.setCommits(commits);
            commitListAdaptor.notifyDataSetChanged();
        }));
    }


    @Override
    protected void onStop() {
        compositeDisposable.dispose();
        super.onStop();
    }
}
