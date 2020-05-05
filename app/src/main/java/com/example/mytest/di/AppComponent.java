package com.example.mytest.di;

import com.example.mytest.MyApp;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 */

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityBuilderModule.class,
        ViewModelModule.class,
        FragmentBuilderModule.class
        })
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(MyApp application);

        AppComponent build();
    }

    void inject(MyApp application);


}