package com.example.github.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.github.ui.commit.CommitListFragmentViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {
    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(MyViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(CommitListFragmentViewModel.class)
    abstract ViewModel bindCommitListActivityViewModel(CommitListFragmentViewModel viewModel);

}