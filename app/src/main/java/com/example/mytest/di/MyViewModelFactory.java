package com.example.mytest.di;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
public class MyViewModelFactory implements ViewModelProvider.Factory {

    private Map<Class<? extends ViewModel>, Provider<ViewModel>> providerMap;

    @Inject
    public MyViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> map) {
        this.providerMap = map;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        Provider<ViewModel> viewModelProvider = providerMap.get(modelClass);
        if (viewModelProvider == null) {
            Set<Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>>> entries = providerMap.entrySet();
            for (Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>> entry : entries) {
                if (modelClass.isAssignableFrom(entry.getKey())) {
                    viewModelProvider = entry.getValue();
                    break;
                }
            }
        }
        if (viewModelProvider != null) {
            return (T) viewModelProvider.get();
        } else {
            throw new IllegalArgumentException("Unknown model class: " + modelClass);
        }
    }
}
