package com.cassio.example.bookstore.di.application;

import android.app.Application;

import com.cassio.example.bookstore.di.glide.GlideModule;
import com.cassio.example.bookstore.di.retrofit.RetrofitModule;
import com.cassio.example.bookstore.di.retrofit.SharedPreferencesModule;
import com.cassio.example.bookstore.repository.ApiServices;
import com.cassio.example.bookstore.repository.FavoritesSharedPreferences;
import com.cassio.example.bookstore.ui.bookmaster.BookMasterPresenter;
import com.cassio.example.bookstore.ui.util.ImageUtil;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Cassio Ribeiro on 10/17/2020
 */
@Singleton
@Component(modules = {ApplicationModule.class, RetrofitModule.class, SharedPreferencesModule.class, GlideModule.class})
public interface IApplicationComponent {

    ApiServices getApiService();
    Application getApplication();
    FavoritesSharedPreferences getFavoritesSharedPreferences();
    ImageUtil getImageUtil();
    void inject(BookMasterPresenter bookMasterPresenter);

}
