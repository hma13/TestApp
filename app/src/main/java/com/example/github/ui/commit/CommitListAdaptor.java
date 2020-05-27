package com.example.github.ui.commit;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.github.R;
import com.example.github.databinding.CommitListItemBinding;
import com.example.github.db.CommitListItemEntity;

import java.util.ArrayList;
import java.util.List;

public class CommitListAdaptor extends RecyclerView.Adapter<CommitViewHolder> {
    private final List<CommitListItemEntity> commits;
    private final OnCommitListItemClickListener listItemClickListener;

    CommitListAdaptor(OnCommitListItemClickListener listItemClickListener) {
        this.listItemClickListener = listItemClickListener;
        this.commits = new ArrayList<>();
    }

    private CommitListItemEntity getItem(int position) {
        return commits.get(position);
    }

    @NonNull
    @Override
    public CommitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CommitListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.commit_list_item, parent, false);
        return new CommitViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CommitViewHolder holder, int position) {
        CommitListItemEntity commit = getItem(position);
        CommitListItemBinding binding = holder.binding;
        binding.setCommit(commit);
        binding.setListener(listItemClickListener);
    }

    @Override
    public long getItemId(int position) {
        //Don't use pos if data changes
        return position;
    }

    @Override
    public int getItemCount() {
        return commits.size();
    }

    void resetCommits(List<CommitListItemEntity> commits) {
        this.commits.clear();
        this.commits.addAll(commits);
        notifyDataSetChanged();
    }

    public interface OnCommitListItemClickListener {
        void onItemClick(CommitListItemEntity item);
    }
}
