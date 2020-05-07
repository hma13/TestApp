package com.example.github.di;

import com.example.github.ui.commit.CommitDetailFragment;
import com.example.github.ui.commit.CommitListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    abstract CommitListFragment contributeCommitListFragment();

    @ContributesAndroidInjector
    abstract CommitDetailFragment contributeCommitDetailFragment();

}