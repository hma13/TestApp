package com.example.github.util;

import androidx.arch.core.executor.testing.CountingTaskExecutorRule;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;

import org.junit.runner.Description;

import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * A Junit rule that registers Architecture Components' background threads as an Espresso idling
 * resource.
 */
public class TaskExecutorWithIdlingResourceRule extends CountingTaskExecutorRule {
    private String id = UUID.randomUUID().toString();
    private CopyOnWriteArrayList<IdlingResource.ResourceCallback> callbacks = new CopyOnWriteArrayList<>();
    private IdlingResource idlingResource = new IdlingResource() {
        @Override
        public String getName() {
            return "architecture components idling resource " + id;
        }

        @Override
        public boolean isIdleNow() {
            return TaskExecutorWithIdlingResourceRule.this.isIdle();
        }

        @Override
        public void registerIdleTransitionCallback(ResourceCallback callback) {
            callbacks.add(callback);
        }
    };

    @Override
    protected void starting(Description description) {
        IdlingRegistry.getInstance().register(idlingResource);
        super.starting(description);
    }

    @Override
    protected void onIdle() {
        super.onIdle();
        for (IdlingResource.ResourceCallback callback : callbacks) {
            callback.onTransitionToIdle();
        }
    }

    @Override
    protected void finished(Description description) {
        try {
            drainTasks(10, TimeUnit.SECONDS);
        } catch (InterruptedException | TimeoutException e) {
            e.printStackTrace();
        }
        callbacks.clear();
        IdlingRegistry.getInstance().unregister(idlingResource);
        super.finished(description);
    }

}