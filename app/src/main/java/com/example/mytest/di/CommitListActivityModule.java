package com.example.mytest.di;

import com.example.mytest.ui.CommitListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class CommitListActivityModule {
    @ContributesAndroidInjector
    abstract CommitListFragment contributeCommitListFragment();

}
