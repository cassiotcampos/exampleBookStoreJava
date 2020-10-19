package com.cassio.example.bookstore.di.retrofit;

import android.app.Application;

import com.cassio.example.bookstore.repository.FavoritesSharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Cassio Ribeiro on 10/17/2020
 */
@Module
public class SharedPreferencesModule {

    @Singleton
    @Provides
    public FavoritesSharedPreferences provideFavoritesSharedPreferences(Application application) {
        return new FavoritesSharedPreferences(application);
    }
}
