package com.example.github.ui.commit;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.github.databinding.CommitDetailItemBinding;

class CommitDetailItemViewHolder extends RecyclerView.ViewHolder {
    CommitDetailItemBinding binding;

    CommitDetailItemViewHolder(@NonNull CommitDetailItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
