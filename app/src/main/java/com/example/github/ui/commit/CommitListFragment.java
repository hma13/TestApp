package com.example.github.ui.commit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.github.R;
import com.example.github.databinding.FragmentCommitListBinding;
import com.example.github.di.Injectable;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class CommitListFragment extends Fragment implements Injectable {
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private CompositeDisposable compositeDisposable;
    private FragmentCommitListBinding binding;
    private CommitListAdaptor commitListAdaptor;
    private CommitListFragmentViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        compositeDisposable = new CompositeDisposable();
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCommitListBinding.inflate(getLayoutInflater());
        FragmentActivity activity = requireActivity();
        activity.setTitle(R.string.commit_list);
        commitListAdaptor = new CommitListAdaptor(item -> {
            CommitListFragmentDirections.ActionCommitListFragmentToCommitDetailFragment action = CommitListFragmentDirections.actionCommitListFragmentToCommitDetailFragment(item.getSha());
            Navigation.findNavController(binding.getRoot()).navigate(action);
        });
        commitListAdaptor.setHasStableIds(true);
        binding.commitList.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        binding.commitList.setLayoutManager(layoutManager);
        binding.commitList.setAdapter(commitListAdaptor);
        DividerItemDecoration decor = new DividerItemDecoration(activity, layoutManager.getOrientation());
        binding.commitList.addItemDecoration(decor);

        binding.container.setOnRefreshListener(() -> viewModel.fetchCommits(true));

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViewModel();
        viewModel.fetchCommits(false);
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
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.commit_list_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (R.id.menu_test == id) {
            viewModel.update(commitListAdaptor.getItem(0).getSha());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStop() {
        compositeDisposable.dispose();
        super.onStop();
    }
}
