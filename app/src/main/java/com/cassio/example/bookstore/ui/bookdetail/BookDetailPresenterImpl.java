package com.cassio.example.bookstore.ui.bookdetail;

import com.cassio.example.bookstore.model.api.BookDetail;
import com.cassio.example.bookstore.model.validator.BookValidator;
import com.cassio.example.bookstore.repository.FavoritesSharedPreferences;

import javax.inject.Inject;

/**
 * Created by Cassio Ribeiro on 10/17/2020
 */
public class BookDetailPresenterImpl implements BookDetailContract.Presenter {

    private final BookDetailContract.View view;
    private final FavoritesSharedPreferences favoritesSharedPreferences;
    private BookDetail bookDetail;

    @Inject
    public BookDetailPresenterImpl(BookDetailContract.View view,
                                   FavoritesSharedPreferences favoritesSharedPreferences) {
        this.view = view;
        this.favoritesSharedPreferences = favoritesSharedPreferences;
    }


    @Override
    public void init(BookDetail bookDetail) {
        this.bookDetail = bookDetail;

        validateFiels();

        view.loadFavoriteButton(favoritesSharedPreferences.isFavorite(bookDetail));
    }

    private void validateFiels() {

        BookValidator bookValidator = new BookValidator(bookDetail);

        if (bookValidator.isTitleAvailable()) {
            view.showTitle(bookValidator.getTitle());
        } else {
            view.showTitleNotAvailable();
        }

        if (bookValidator.hasValidImg()) {
            view.loadThumb(bookValidator.getValidImgUrl());
        } else {
            view.loadThumDefault();
        }

        if (bookValidator.isAuthorsAvailable()) {
            view.showAuthors(bookValidator.getAuthorsFormated());
        } else {
            view.hideAuthors();
        }

        if (bookValidator.isBuyLinkAvailable()) {
            view.showBuyLink(bookValidator.getValidBuyLink());
        } else {
            view.hideBuyLink();
        }

        view.showDescription(bookValidator.getDescriptionOrSnippets());
    }

    @Override
    public void onClickFavorite() {

        if (favoritesSharedPreferences.isFavorite(bookDetail)) {
            favoritesSharedPreferences.removeFavorite(bookDetail);
            view.loadFavoriteButton(false);
        } else {
            favoritesSharedPreferences.addToFavorites(bookDetail);
            view.loadFavoriteButton(true);
        }

        view.refreshFavoritesActivity();
    }

}
