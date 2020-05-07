package com.example.github.di;

import com.example.github.ui.commit.HomeActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(/*modules = CommitListActivityModule.class*/)
    abstract HomeActivity contributeCommitListActivity();

}