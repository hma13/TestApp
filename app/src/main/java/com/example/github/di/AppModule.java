package com.example.github.di;

import android.content.res.Resources;

import com.example.github.BuildConfig;
import com.example.github.MyApp;
import com.example.github.api.GithubApiClient;
import com.example.github.api.RemoteRepo;
import com.example.github.repo.DataRepo;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import timber.log.Timber;

@Module
abstract class AppModule {

    @Provides
    static Resources provideResources(MyApp application) {
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
