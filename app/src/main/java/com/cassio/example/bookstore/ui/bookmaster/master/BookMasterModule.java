package com.cassio.example.bookstore.ui.bookmaster.master;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Cassio Ribeiro on 10/17/2020
 */
@Module
public abstract class BookMasterModule {


    @Provides
    static BookMasterContract.Presenter provideBookMasterPresenter(BookMasterPresenterImpl bookMasterPresenter){
        return bookMasterPresenter;
    }

    @Binds
    abstract BookMasterContract.View provideMainView(BookMasterActivity mainActivity);
}
