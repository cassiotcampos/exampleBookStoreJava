package com.cassio.example.bookstore.ui.bookmaster.favorites;


import com.cassio.example.bookstore.model.api.BookDetail;
import com.cassio.example.bookstore.model.api.BooksMaster;
import com.cassio.example.bookstore.repository.FavoritesSharedPreferences;

import java.util.ArrayList;

import javax.inject.Inject;


/**
 * Created by Cassio Ribeiro on 10/17/2020
 */
public class BookFavoritesPresenterImpl implements BooksFavoritesContract.Presenter {

    private BooksFavoritesContract.View view;
    private FavoritesSharedPreferences favoritesSharedPreferences;

    @Inject
    public BookFavoritesPresenterImpl(BooksFavoritesContract.View view,
                                      FavoritesSharedPreferences favoritesSharedPreferences) {

        this.view = view;
        this.favoritesSharedPreferences = favoritesSharedPreferences;
    }

    @Override
    public void loadFavoritesFromSharedP() {
        view.hideProgress();
        ArrayList<BookDetail> mFavoriteBooks = favoritesSharedPreferences.getFavoriteBooks();
        BooksMaster booksMaster = new BooksMaster();
        booksMaster.setTotalItems(mFavoriteBooks.size());
        booksMaster.setBooks(mFavoriteBooks);
        view.showBookList(booksMaster);
    }
}
