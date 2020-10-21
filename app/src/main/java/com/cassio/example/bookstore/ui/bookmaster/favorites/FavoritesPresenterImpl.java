package com.cassio.example.bookstore.ui.bookmaster.favorites;


import com.cassio.example.bookstore.model.api.BookDetail;
import com.cassio.example.bookstore.model.api.BooksMaster;
import com.cassio.example.bookstore.repository.FavoritesSharedPreferences;

import java.util.ArrayList;

import javax.inject.Inject;


/**
 * Created by Cassio Ribeiro on 10/17/2020
 */
public class FavoritesPresenterImpl implements FavoritesContract.Presenter {

    private FavoritesContract.View view;
    private FavoritesSharedPreferences favoritesSharedPreferences;

    @Inject
    public FavoritesPresenterImpl(FavoritesContract.View view,
                                  FavoritesSharedPreferences favoritesSharedPreferences) {

        this.view = view;
        this.favoritesSharedPreferences = favoritesSharedPreferences;
    }

    @Override
    public void loadFavoritesFromSharedP() {
        ArrayList<BookDetail> mFavoriteBooks = favoritesSharedPreferences.getFavoriteBooks();
        BooksMaster booksMaster = new BooksMaster();
        booksMaster.setTotalItems(mFavoriteBooks.size());
        booksMaster.setBooks(mFavoriteBooks);
        view.showBookList(booksMaster);
    }
}
