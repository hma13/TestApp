package com.example.mytest.di;

import com.example.mytest.ui.CommitListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = CommitListActivityModule.class)
    abstract CommitListActivity contributeCommitListActivity();

}