package com.example.github.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.github.R;
import com.example.github.databinding.FragmentCommitListBinding;
import com.example.github.di.Injectable;

import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class CommitListFragment extends Fragment implements Injectable {
    private CompositeDisposable compositeDisposable;
    private FragmentCommitListBinding binding;
    private CommitListAdaptor commitListAdaptor;

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private CommitListFragmentViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        compositeDisposable = new CompositeDisposable();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCommitListBinding.inflate(getLayoutInflater());
        Context context = Objects.requireNonNull(getContext());
        commitListAdaptor = new CommitListAdaptor(context);
        commitListAdaptor.setHasStableIds(true);
        binding.commitList.setHasFixedSize(true);
        binding.commitList.setLayoutManager(new LinearLayoutManager(context));
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
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        LiveData<Boolean> fetchingLiveData = viewModel.getFetchingLiveData();
        fetchingLiveData.observe(viewLifecycleOwner, fetching -> {
            binding.container.setRefreshing(Boolean.TRUE == fetching);
        });

        viewModel.getCommitsLiveData().observe(viewLifecycleOwner, pair -> {
            if (pair != null) {
                if (pair.second != null) {
                    Toast.makeText(getContext(), R.string.api_error, Toast.LENGTH_LONG).show();
                } else {
                    commitListAdaptor.resetCommits(pair.first);
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
