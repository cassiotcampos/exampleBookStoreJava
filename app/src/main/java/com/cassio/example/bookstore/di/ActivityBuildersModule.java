package com.cassio.example.bookstore.di;

import com.cassio.example.bookstore.ui.bookdetail.BookDetailActivity;
import com.cassio.example.bookstore.ui.bookdetail.BookDetailFragmentProvider;
import com.cassio.example.bookstore.ui.bookdetail.BookDetailModule;
import com.cassio.example.bookstore.ui.bookmaster.favorites.BookFavoritesActivity;
import com.cassio.example.bookstore.ui.bookmaster.favorites.BookFavoritesModule;
import com.cassio.example.bookstore.ui.bookmaster.master.BookMasterActivity;
import com.cassio.example.bookstore.ui.bookmaster.master.BookMasterModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Cassio Ribeiro on 10/18/2020
 */
@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = {BookMasterModule.class, BookDetailFragmentProvider.class})
    abstract BookMasterActivity bookMasterActivity();

    @ContributesAndroidInjector(modules = {BookFavoritesModule.class, BookDetailFragmentProvider.class})
    abstract BookFavoritesActivity bookFavoritesActivity();

    @ContributesAndroidInjector(modules = {BookDetailModule.class, BookDetailFragmentProvider.class})
    abstract BookDetailActivity bookDetailActivity();

}
