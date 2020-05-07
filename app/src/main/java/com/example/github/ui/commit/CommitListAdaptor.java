package com.example.github.ui.commit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.github.data.CommitInfo;
import com.example.github.data.CommitListItem;
import com.example.github.databinding.CommitListItemBinding;

import java.util.ArrayList;
import java.util.List;

class CommitListAdaptor extends RecyclerView.Adapter<CommitViewHolder> {
    private final List<CommitListItem> commits;

    CommitListAdaptor(@NonNull Context context) {
        this.commits = new ArrayList<>();
    }

    private CommitListItem getItem(int position) {
        return commits.get(position);
    }

    @NonNull
    @Override
    public CommitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CommitListItemBinding binding = CommitListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CommitViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CommitViewHolder holder, int position) {
        CommitListItem commit = getItem(position);
        CommitListItemBinding binding = holder.binding;
        if (commit != null) {
            binding.hash.setText(commit.getSha());
            CommitInfo commitInfo = commit.getCommitInfo();
            if (commitInfo != null) {
                binding.author.setText(commitInfo.getAuthorName());
                binding.message.setText(commitInfo.getMessage());
            }
        } else {
            binding.hash.setText(null);
            binding.author.setText(null);
            binding.message.setText(null);
        }
    }

    @Override
    public long getItemId(int position) {
        //Don't use pos if data will change
        return position;
    }

    @Override
    public int getItemCount() {
        return commits.size();
    }

    void resetCommits(List<CommitListItem> commits) {
        this.commits.clear();
        this.commits.addAll(commits);
        notifyDataSetChanged();
    }
}
