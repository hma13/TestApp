package com.example.github.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.github.ui.commit.CommitDetailFragmentViewModel;
import com.example.github.ui.commit.CommitListFragmentViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
abstract class ViewModelModule {
    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(MyViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(CommitListFragmentViewModel.class)
    abstract ViewModel bindCommitListFragmentViewModel(CommitListFragmentViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CommitDetailFragmentViewModel.class)
    abstract ViewModel bindCommitDetailFragmentViewModel(CommitDetailFragmentViewModel viewModel);

}