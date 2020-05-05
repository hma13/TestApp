package com.example.mytest.di;

import android.content.res.Resources;

import com.example.mytest.BuildConfig;
import com.example.mytest.TestApp;
import com.example.mytest.api.GithubApiClient;
import com.example.mytest.api.RemoteRepo;
import com.example.mytest.repo.DataRepo;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import timber.log.Timber;

@Module
abstract class AppModule {

    @Provides
    static Resources provideResources(TestApp application) {
        return application.getResources();
    }

    @Provides
    @Reusable
    static Timber.Tree providerTree() {
        return BuildConfig.DEBUG ? new Timber.DebugTree() : null;
    }

    @Provides
    @Reusable
    static DataRepo provideDataRepo(GithubApiClient client) {
        return new RemoteRepo(client);
    }


}
