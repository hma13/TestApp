package com.example.github.ui.commit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.github.data.Commit;
import com.example.github.data.CommitDetail;
import com.example.github.databinding.CommitListItemBinding;

import java.util.ArrayList;
import java.util.List;

class CommitListAdaptor extends RecyclerView.Adapter<CommitViewHolder> {
    private final List<Commit> commits;

    CommitListAdaptor(@NonNull Context context) {
        this.commits = new ArrayList<>();
    }

    private Commit getItem(int position) {
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
        Commit commit = getItem(position);
        CommitListItemBinding binding = holder.binding;
        if (commit != null) {
            binding.hash.setText(commit.getSha());
            CommitDetail commitDetail = commit.getCommitDetail();
            if (commitDetail != null) {
                binding.author.setText(commitDetail.getAuthorName());
                binding.message.setText(commitDetail.getMessage());
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

    void resetCommits(List<Commit> commits) {
        this.commits.clear();
        this.commits.addAll(commits);
        notifyDataSetChanged();
    }
}
