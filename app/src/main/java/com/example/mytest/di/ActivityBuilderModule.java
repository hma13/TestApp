package com.example.mytest.di;

import com.example.mytest.CommitListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 *
 */

@Module
public abstract class ActivityBuilderModule {
    @ContributesAndroidInjector()
    abstract CommitListActivity contributeCommitListActivity();

}