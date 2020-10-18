package com.cassio.example.bookstore.ui.bookmaster;

import com.cassio.example.bookstore.model.api.BookDetail;

/**
 * Created by Cassio Ribeiro on 10/17/2020
 */
public interface BookRowCallback {
    void onLoadMoreBooksUnavailable(String msg);
    void onBookClicked(BookDetail bookDetail);
    void lastBookBinded();
}
