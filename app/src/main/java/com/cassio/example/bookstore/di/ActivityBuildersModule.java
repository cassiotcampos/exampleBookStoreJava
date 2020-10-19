package com.cassio.example.bookstore.di;

import com.cassio.example.bookstore.ui.bookdetail.BookDetailActivity;
import com.cassio.example.bookstore.ui.bookdetail.BookDetailFragmentProvider;
import com.cassio.example.bookstore.ui.bookdetail.BookDetailModule;
import com.cassio.example.bookstore.ui.bookmaster.favorites.FavoritesActivity;
import com.cassio.example.bookstore.ui.bookmaster.favorites.FavoritesModule;
import com.cassio.example.bookstore.ui.bookmaster.master.MasterActivity;
import com.cassio.example.bookstore.ui.bookmaster.master.MasterModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Cassio Ribeiro on 10/18/2020
 */
@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = {MasterModule.class, BookDetailFragmentProvider.class})
    abstract MasterActivity bookMasterActivity();

    @ContributesAndroidInjector(modules = {FavoritesModule.class, BookDetailFragmentProvider.class})
    abstract FavoritesActivity bookFavoritesActivity();

    @ContributesAndroidInjector(modules = {BookDetailModule.class, BookDetailFragmentProvider.class})
    abstract BookDetailActivity bookDetailActivity();

}
