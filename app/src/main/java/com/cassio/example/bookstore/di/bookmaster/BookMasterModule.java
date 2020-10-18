package com.cassio.example.bookstore.di.bookmaster;

import android.content.Context;

import com.cassio.example.bookstore.di.scope.ActivityScope;
import com.cassio.example.bookstore.ui.bookmaster.BookMasterActivity;
import com.cassio.example.bookstore.ui.bookmaster.BookMasterNavigation;
import com.cassio.example.bookstore.ui.bookmaster.BookMasterPresenter;
import com.cassio.example.bookstore.ui.bookmaster.IBookMaster;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Cassio Ribeiro on 10/17/2020
 */
@Module
public class BookMasterModule {

    private final BookMasterActivity bookMasterActivity;

    public BookMasterModule(BookMasterActivity bookMasterActivity) {
        this.bookMasterActivity = bookMasterActivity;
    }

    @Provides
    @ActivityScope
    IBookMaster.IMasterNavigation provideMasterNavigation(BookMasterNavigation bookMasterNavigation){
        return bookMasterNavigation;
    }

    @Provides
    @ActivityScope
    IBookMaster.IPresenter provideBookMasterPresenter(BookMasterPresenter bookMasterPresenter){
        return bookMasterPresenter;
    }

    @Provides
    @ActivityScope
    Context provideContext(){
        return bookMasterActivity;
    }

    @Provides
    @ActivityScope
    BookMasterActivity bookMasterActivity(){
        return bookMasterActivity;
    }
}
