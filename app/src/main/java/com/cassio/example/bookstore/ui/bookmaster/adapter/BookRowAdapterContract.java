package com.cassio.example.bookstore.ui.bookmaster.adapter;

import com.cassio.example.bookstore.model.api.BookDetail;
import com.cassio.example.bookstore.model.api.BooksMaster;

/**
 * Created by Cassio Ribeiro on 10/17/2020
 */
public interface BookRowAdapterContract {

    void addMoreBooks(BooksMaster booksMaster);

    void removeBook(BookDetail bookToBeRemoved);

    BooksMaster getBookMaster();

    int getLastPosition();

    void showProgress();

    void hideProgress();

    void showErrorTryAgain();

    void showNoMoreResults();
}
