package com.example.mytest;

import android.app.Application;

import com.example.mytest.di.AppInjector;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import timber.log.Timber;

public class MyApp extends Application implements HasAndroidInjector {
    @Inject
    DispatchingAndroidInjector<Object> dispatchingAndroidInjector;

    @Inject
    Timber.Tree tree;

    @Override
    public void onCreate() {
        super.onCreate();
        AppInjector.init(this);
        Timber.plant(tree);

        Timber.d("app onCreate");
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return dispatchingAndroidInjector;
    }
}
