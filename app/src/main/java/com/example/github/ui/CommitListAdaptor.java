package com.example.github.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.example.github.data.Commit;
import com.example.github.data.CommitDetail;
import com.example.github.databinding.CommitListItemBinding;

import java.util.ArrayList;
import java.util.List;

class CommitListAdaptor extends ArrayAdapter<Commit> {
    private final Context context;
    private final List<Commit> commits;

    public CommitListAdaptor(@NonNull Context context) {
        super(context, -1);
        this.context = context;
        this.commits = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return commits.size();
    }

    @Override
    public Commit getItem(int position) {
        return commits.get(position);
    }

    @Override
    public long getItemId(int position) {
        //Don't use pos if data will change
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommitListItemBinding binding;
        if (convertView == null) {
            binding = CommitListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        } else {
            binding = CommitListItemBinding.bind(convertView);
        }
        Commit commit = getItem(position);
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

        return binding.getRoot();
    }

    public void setCommits(List<Commit> commits) {
        this.commits.clear();
        this.commits.addAll(commits);
        notifyDataSetChanged();
    }
}
