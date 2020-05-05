package com.example.mytest.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.mytest.ui.CommitListFragmentViewModel;

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