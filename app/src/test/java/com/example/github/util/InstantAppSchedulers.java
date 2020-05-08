package com.example.github.util;

import androidx.annotation.NonNull;

import com.example.github.AppSchedulers;

import java.util.concurrent.TimeUnit;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.schedulers.ExecutorScheduler;

public class InstantAppSchedulers extends AppSchedulers {
    private static final Scheduler IMMEDIATE = new Scheduler() {
        @Override
        public Disposable scheduleDirect(@NonNull Runnable run, long delay, @NonNull TimeUnit unit) {
            // this prevents StackOverflowErrors when scheduling with a delay
            return super.scheduleDirect(run, 0, unit);
        }

        @Override
        public Worker createWorker() {
            return new ExecutorScheduler.ExecutorWorker(Runnable::run);
        }
    };

    public InstantAppSchedulers() {
        super(IMMEDIATE, IMMEDIATE, IMMEDIATE);
    }
}
