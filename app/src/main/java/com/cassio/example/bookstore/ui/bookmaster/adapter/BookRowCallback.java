package com.cassio.example.bookstore.ui.bookmaster.adapter;

import com.cassio.example.bookstore.model.api.BookDetail;

/**
 * Created by Cassio Ribeiro on 10/17/2020
 */
public interface BookRowCallback {
    void onBookClicked(BookDetail bookDetail);
    void lastBookBinded();
}
