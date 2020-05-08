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
    private final Scheduler io;
    private final Scheduler main;

    @Inject
    AppSchedulers() {
        single = Schedulers.single();
        io = Schedulers.from(Executors.newFixedThreadPool(THREAD_COUNT));
        main = AndroidSchedulers.mainThread();
    }

    @NonNull
    public Scheduler single() {
        return single;
    }

    @NonNull
    public Scheduler io() {
        return io;
    }

    @NonNull
    public Scheduler main() {
        return main;
    }
}