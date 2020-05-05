package com.example.mytest;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.mytest.databinding.ActivityCommitListBinding;
import com.example.mytest.di.MyViewModelFactory;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.reactivex.disposables.CompositeDisposable;

public class CommitListActivity extends AppCompatActivity {

    private CompositeDisposable compositeDisposable;
    private ActivityCommitListBinding binding;
    private CommitListAdaptor commitListAdaptor;

    @Inject
    MyViewModelFactory viewModelFactory;
    private CommitListActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        compositeDisposable = new CompositeDisposable();
        super.onCreate(savedInstanceState);
        binding = ActivityCommitListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        commitListAdaptor = new CommitListAdaptor(this);
        binding.commitList.setAdapter(commitListAdaptor);

        viewModel = new ViewModelProvider(this, viewModelFactory).get(CommitListActivityViewModel.class);
        binding.container.setOnRefreshListener(() -> viewModel.fetchCommits());

        viewModel.getFetchingLiveData().observe(this, fetching -> {
            binding.container.setRefreshing(Boolean.TRUE == fetching);
        });

        viewModel.getCommitsLiveData().observe(this, commits -> {
            commitListAdaptor.setCommits(commits);
        });

        viewModel.fetchCommits();
    }


    @Override
    protected void onStop() {
        compositeDisposable.dispose();
        super.onStop();
    }
}
