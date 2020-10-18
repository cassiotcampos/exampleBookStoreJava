package com.cassio.example.bookstore.di.bookdetail;

import com.cassio.example.bookstore.di.scope.FragmentScope;
import com.cassio.example.bookstore.ui.bookdetail.BookDetailPresenter;
import com.cassio.example.bookstore.ui.bookdetail.IBookDetail;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Cassio Ribeiro on 10/17/2020
 */
@Module
public class BookDetailModule {

    @Provides
    @FragmentScope
    IBookDetail.Presenter provideBookDetailPresenter(BookDetailPresenter bookDetailPresenter){
        return bookDetailPresenter;
    }
}
