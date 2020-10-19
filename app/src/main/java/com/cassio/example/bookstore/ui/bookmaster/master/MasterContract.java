package com.cassio.example.bookstore.ui.bookmaster.master;


import com.cassio.example.bookstore.model.api.BooksMaster;
import com.cassio.example.bookstore.ui.base.BasePresenter;
import com.cassio.example.bookstore.ui.base.BaseView;
import com.cassio.example.bookstore.ui.bookmaster.adapter.BookRowCallback;

public interface MasterContract {

    interface View extends BaseView, BookRowCallback {

        void showProgress();

        void hideProgress();

        void showBookList(BooksMaster booksMaster);

        void showApiErrorTryAgain();
    }

    interface Presenter extends BasePresenter {

        void loadBooksFromApi();

        void loadMoreBooks();

        boolean hasFavorites();
    }

}
