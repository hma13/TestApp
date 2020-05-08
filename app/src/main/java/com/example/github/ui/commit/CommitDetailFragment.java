package com.example.github.ui.commit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.github.R;
import com.example.github.databinding.FragmentCommitDetailBinding;
import com.example.github.di.Injectable;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;

public class CommitDetailFragment extends Fragment implements Injectable {
    private CompositeDisposable compositeDisposable;
    private FragmentCommitDetailBinding binding;
    private CommitDetailFragmentViewModel viewModel;

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private String commitHash;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        compositeDisposable = new CompositeDisposable();
        super.onCreate(savedInstanceState);
        Bundle bundle = requireArguments();
        commitHash = CommitDetailFragmentArgs.fromBundle(bundle).getCommitHash();
        Timber.d("commit hash: %s", commitHash);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCommitDetailBinding.inflate(getLayoutInflater());
        requireActivity().setTitle(R.string.commit_detail);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initViewModel();
        viewModel.fetchCommit(commitHash);
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this, viewModelFactory).get(CommitDetailFragmentViewModel.class);
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        LiveData<Boolean> fetchingLiveData = viewModel.getFetchingLiveData();
        fetchingLiveData.observe(viewLifecycleOwner, fetching -> {
            binding.container.setRefreshing(Boolean.TRUE == fetching);
        });

        viewModel.getCommitLiveData().observe(viewLifecycleOwner, pair -> {
            if (pair != null) {

            }
        });
    }

    @Override
    public void onStop() {
        compositeDisposable.dispose();
        super.onStop();
    }
}
