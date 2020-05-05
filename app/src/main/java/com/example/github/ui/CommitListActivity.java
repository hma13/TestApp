package com.example.github.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.github.databinding.ActivityCommitListBinding;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

public class CommitListActivity extends AppCompatActivity implements HasAndroidInjector {

    private ActivityCommitListBinding binding;

    @Inject
    DispatchingAndroidInjector<Object> dispatchingAndroidInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCommitListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return dispatchingAndroidInjector;
    }
}
