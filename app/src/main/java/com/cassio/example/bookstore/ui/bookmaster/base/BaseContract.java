package com.cassio.example.bookstore.ui.bookmaster.base;


import com.cassio.example.bookstore.ui.base.BaseView;
import com.cassio.example.bookstore.ui.bookmaster.adapter.BookRowCallback;

public interface BaseContract {


    interface View extends BaseView, BookRowCallback {
        boolean isTwoPanel();
    }

}
