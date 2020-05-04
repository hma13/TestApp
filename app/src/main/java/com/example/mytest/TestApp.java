package com.example.mytest;

import android.app.Application;

import com.example.mytest.di.AppComponent;
import com.example.mytest.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import timber.log.Timber;

public class TestApp extends Application implements HasAndroidInjector {
    @Inject
    DispatchingAndroidInjector<Object> dispatchingAndroidInjector;

    @Inject
    Timber.Tree tree;

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = initAppComponent();
        appComponent.inject(this);
        Timber.plant(tree);

        Timber.d("app onCreate");
    }

    protected AppComponent initAppComponent() {
        return DaggerAppComponent.builder().application(this).build();
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return dispatchingAndroidInjector;
    }
}
