package com.cassio.example.bookstore.ui.bookmaster;

import com.cassio.example.bookstore.model.api.BookDetail;

import javax.inject.Inject;

/**
 * Created by Cassio Ribeiro on 10/17/2020
 */
public  class BookMasterNavigation implements IBookMaster.IMasterNavigation{

    BookMasterActivity masterActivity;

    @Inject
    public BookMasterNavigation(BookMasterActivity masterActivity) {
        this.masterActivity = masterActivity;
    }

    @Override
    public void goToBookDetail(BookDetail bookDetail) {

    }
}