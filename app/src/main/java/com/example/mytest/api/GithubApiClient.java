package com.example.mytest.api;

import com.example.mytest.BuildConfig;
import com.google.gson.Gson;

import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public class GithubApiClient {
    public static final String GITHUB_URL = "https://api.github.com";
    private final OkHttpClient client;
    private final GithubApiService gitHubApiService;

    public GithubApiClient() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            clientBuilder.addInterceptor(httpLoggingInterceptor);
        }
        client = clientBuilder.build();

        Retrofit retroFit = new Retrofit.Builder()
                .client(client)
                .baseUrl(GITHUB_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        gitHubApiService = retroFit.create(GithubApiService.class);
    }

    public GithubApiService getGitHubApiService() {
        return gitHubApiService;
    }
}
