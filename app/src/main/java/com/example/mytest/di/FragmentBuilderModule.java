package com.example.mytest.di;

import com.example.mytest.ui.CommitListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    abstract CommitListFragment contributeCommitListFragment();

}