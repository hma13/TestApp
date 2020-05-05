package com.example.mytest.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mytest.R;
import com.example.mytest.databinding.FragmentCommitListBinding;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import io.reactivex.disposables.CompositeDisposable;

public class CommitListFragment extends Fragment {

    private CompositeDisposable compositeDisposable;
    private FragmentCommitListBinding binding;
    private CommitListAdaptor commitListAdaptor;

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private CommitListFragmentViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        compositeDisposable = new CompositeDisposable();
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCommitListBinding.inflate(getLayoutInflater());
        commitListAdaptor = new CommitListAdaptor(getContext());
        binding.commitList.setAdapter(commitListAdaptor);
        binding.container.setOnRefreshListener(() -> viewModel.fetchCommits());

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViewModel();
        viewModel.fetchCommits();
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this, viewModelFactory).get(CommitListFragmentViewModel.class);
        viewModel.getFetchingLiveData().observe(getViewLifecycleOwner(), fetching -> {
            binding.container.setRefreshing(Boolean.TRUE == fetching);
        });

        viewModel.getCommitsLiveData().observe(getViewLifecycleOwner(), pair -> {
            if (pair != null) {
                if (pair.second != null) {
                    Toast.makeText(getContext(), R.string.api_error, Toast.LENGTH_LONG).show();
                } else {
                    commitListAdaptor.setCommits(pair.first);
                }
            }
        });
    }


    @Override
    public void onStop() {
        compositeDisposable.dispose();
        super.onStop();
    }
}
