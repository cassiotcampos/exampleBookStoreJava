package com.cassio.example.bookstore.ui.bookmaster.favorites;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Cassio Ribeiro on 10/17/2020
 */
@Module
public abstract class BookFavoritesModule {

    @Provides
    static BooksFavoritesContract.Presenter provideFavoritesPresenter(BookFavoritesPresenterImpl bookFavoritesPresenter){
        return bookFavoritesPresenter;
    }

    @Binds
    abstract BooksFavoritesContract.View provideFavoritesView(BookFavoritesActivity favoritesActivity);
}
