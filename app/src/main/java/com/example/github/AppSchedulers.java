package com.example.github;

import androidx.annotation.NonNull;

import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class AppSchedulers {
    private static final int THREAD_COUNT = 3;
    private final Scheduler single;
    private final Scheduler network;
    private final Scheduler main;

    @Inject
    AppSchedulers() {
        this(Schedulers.single(), Schedulers.from(Executors.newFixedThreadPool(THREAD_COUNT)), AndroidSchedulers.mainThread());
    }

    protected AppSchedulers(Scheduler single, Scheduler network, Scheduler main) {
        this.single = single;
        this.network = network;
        this.main = main;
    }

    @NonNull
    public Scheduler single() {
        return single;
    }

    @NonNull
    public Scheduler network() {
        return network;
    }

    @NonNull
    public Scheduler main() {
        return main;
    }
}