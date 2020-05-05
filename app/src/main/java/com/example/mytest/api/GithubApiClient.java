package com.example.mytest.api;

import com.example.mytest.BuildConfig;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public class GithubApiClient {
    private static final String GITHUB_URL = "https://api.github.com";
    private OkHttpClient client;
    private GithubApiService gitHubApiService;

    @Inject
    public GithubApiClient() {
        init();
    }

    private void init() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("Accept", "application/vnd.github.v3+json")
                    .method(original.method(), original.body())
                    .build();
            return chain.proceed(request);
        });

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            clientBuilder.addInterceptor(httpLoggingInterceptor);
        }

        client = clientBuilder.build();

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .registerTypeAdapter(Long.class, new CalendarTypeAdapter())
                .create();

        Retrofit retroFit = new Retrofit.Builder()
                .client(client)
                .baseUrl(GITHUB_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        gitHubApiService = retroFit.create(GithubApiService.class);
    }

    public GithubApiService getGitHubApiService() {
        return gitHubApiService;
    }
}
