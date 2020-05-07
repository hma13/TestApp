package com.example.github.ui.commit;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.github.databinding.CommitListItemBinding;

class CommitViewHolder extends RecyclerView.ViewHolder {
    CommitListItemBinding binding;

    CommitViewHolder(@NonNull CommitListItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
