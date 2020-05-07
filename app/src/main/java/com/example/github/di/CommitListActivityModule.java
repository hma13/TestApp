package com.example.github.di;

import com.example.github.ui.commit.CommitListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class CommitListActivityModule {
    @ContributesAndroidInjector
    abstract CommitListFragment contributeCommitListFragment();

}
