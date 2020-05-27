package com.example.github.di;

import android.app.Application;

import androidx.room.Room;

import com.example.github.BuildConfig;
import com.example.github.api.GithubApiClient;
import com.example.github.api.GithubApiService;
import com.example.github.db.AppDb;
import com.example.github.db.CommitListItemEntityDao;

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

    @Singleton
    @Provides
    static AppDb provideDb(Application app) {
        return Room.databaseBuilder(app, AppDb.class, "app.db").build();
    }

    @Singleton
    @Provides
    static CommitListItemEntityDao provideCommitListItemEntityDao(AppDb appDb) {
        return appDb.commitListItemEntityDao();
    }

}
