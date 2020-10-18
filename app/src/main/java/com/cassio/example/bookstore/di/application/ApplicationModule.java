package com.cassio.example.bookstore.di.application;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Cassio Ribeiro on 10/17/2020
 */
@Module
public class ApplicationModule {
    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    Application provideApplication() {
        return application;
    }



}
