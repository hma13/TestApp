package com.example.github.ui.commit;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.github.R;
import com.example.github.databinding.CommitDetailItemBinding;

import java.util.ArrayList;
import java.util.List;

public class CommitDetailAdaptor extends RecyclerView.Adapter<CommitDetailItemViewHolder> {
    private final List<CommitDetailListItem> items;

    CommitDetailAdaptor() {
        items = new ArrayList<>();
    }

    private CommitDetailListItem getItem(int position) {
        return items.get(position);
    }

    @NonNull
    @Override
    public CommitDetailItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CommitDetailItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.commit_detail_item, parent, false);
        return new CommitDetailItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CommitDetailItemViewHolder holder, int position) {
        CommitDetailListItem commit = getItem(position);
        CommitDetailItemBinding binding = holder.binding;
        binding.setItem(commit);
    }

    @Override
    public long getItemId(int position) {
        //Don't use pos if data changes
        return position;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    void resetCommits(List<CommitDetailListItem> commits) {
        this.items.clear();
        this.items.addAll(commits);
        notifyDataSetChanged();
    }

}
