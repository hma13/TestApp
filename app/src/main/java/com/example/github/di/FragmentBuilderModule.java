package com.example.github.di;

import com.example.github.ui.commit.CommitListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    abstract CommitListFragment contributeCommitListFragment();

}