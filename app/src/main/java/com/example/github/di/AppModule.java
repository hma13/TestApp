package com.example.github.di;

import com.example.github.BuildConfig;
import com.example.github.api.GithubApiClient;
import com.example.github.api.GithubApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import timber.log.Timber;

@Module
abstract class AppModule {

    @Provides
    @Singleton
    static GithubApiService provideGitHubService(GithubApiClient client) {
        return client.getGitHubApiService();
    }

    @Provides
    @Reusable
    static Timber.Tree providerTree() {
        return BuildConfig.DEBUG ? new Timber.DebugTree() : null;
    }

}
