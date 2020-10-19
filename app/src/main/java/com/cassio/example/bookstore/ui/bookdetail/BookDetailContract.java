package com.cassio.example.bookstore.ui.bookdetail;

import com.cassio.example.bookstore.model.api.BookDetail;
import com.cassio.example.bookstore.ui.base.BasePresenter;
import com.cassio.example.bookstore.ui.base.BaseView;

/**
 * Created by Cassio Ribeiro on 10/17/2020
 */
public interface BookDetailContract {

    interface View extends BaseView {

        void loadFavoriteButton(boolean isFavorite);

        void showTitle(String title);

        void showTitleNotAvailable();

        void loadThumb(String validImgUrl);

        void loadThumDefault();

        void showAuthors(String authorsFormated);

        void hideAuthors();

        void showBuyLink(String validBuyLink);

        void hideBuyLink();

        void showDescription(String descriptionOrSnippets);

        void refreshFavoritesActivity();
    }

    interface Presenter extends BasePresenter {

        void init(BookDetail bookDetail);

        void onClickFavorite();
    }
}
