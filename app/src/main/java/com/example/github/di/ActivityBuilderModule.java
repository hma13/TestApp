package com.example.github.di;

import com.example.github.ui.commit.CommitListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(/*modules = CommitListActivityModule.class*/)
    abstract CommitListActivity contributeCommitListActivity();

}