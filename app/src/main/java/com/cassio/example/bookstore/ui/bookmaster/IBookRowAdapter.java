package com.cassio.example.bookstore.ui.bookmaster;

import com.cassio.example.bookstore.model.api.BookDetail;
import com.cassio.example.bookstore.model.api.BooksMaster;

/**
 * Created by Cassio Ribeiro on 10/17/2020
 */
public interface IBookRowAdapter {

    void addMoreBooks(BooksMaster booksMaster);

    void removeBook(BookDetail bookToBeRemoved);

    void setCallback(BookRowCallback bookRowCallback);

    BooksMaster getBookMaster();
}
