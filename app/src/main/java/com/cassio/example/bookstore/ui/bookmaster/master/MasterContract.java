package com.cassio.example.bookstore.ui.bookmaster.master;


import android.content.Intent;
import android.os.Bundle;

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

        void showNoMoreResults();
    }

    interface Presenter extends BasePresenter {

        void loadBooksOnCreate(Intent savedInstanceState);

        void loadMoreBooks();

        boolean hasFavorites();

        void saveState(Bundle outState);
    }

}
