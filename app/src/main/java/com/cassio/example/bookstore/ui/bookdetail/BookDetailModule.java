package com.cassio.example.bookstore.ui.bookdetail;

import com.cassio.example.bookstore.repository.FavoritesSharedPreferences;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Cassio Ribeiro on 10/17/2020
 */
@Module
public abstract class BookDetailModule {

    @Provides
    static BookDetailContract.Presenter provideDetailPresenter(BookDetailContract.View view, FavoritesSharedPreferences favoritesSharedPreferences) {
        return new BookDetailPresenterImpl(view, favoritesSharedPreferences);
    }

    @Binds
    abstract BookDetailContract.View provideDetailView(BookDetailFragment bookDetailFragment);
}
