package com.cassio.example.bookstore.ui.bookmaster.favorites;


import com.cassio.example.bookstore.model.api.BooksMaster;
import com.cassio.example.bookstore.ui.base.BasePresenter;
import com.cassio.example.bookstore.ui.base.BaseView;
import com.cassio.example.bookstore.ui.bookmaster.adapter.BookRowCallback;

public interface FavoritesContract {


    interface View extends BaseView, BookRowCallback {

        void showBookList(BooksMaster booksMaster);

        void hideProgress();
    }

    interface Presenter extends BasePresenter {

        void loadFavoritesFromSharedP();
    }

}
