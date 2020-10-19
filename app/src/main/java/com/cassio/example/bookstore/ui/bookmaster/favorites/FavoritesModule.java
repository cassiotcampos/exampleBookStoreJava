package com.cassio.example.bookstore.ui.bookmaster.favorites;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Cassio Ribeiro on 10/17/2020
 */
@Module
public abstract class FavoritesModule {

    @Provides
    static FavoritesContract.Presenter provideFavoritesPresenter(FavoritesPresenterImpl bookFavoritesPresenter){
        return bookFavoritesPresenter;
    }

    @Binds
    abstract FavoritesContract.View provideFavoritesView(FavoritesActivity favoritesActivity);
}
