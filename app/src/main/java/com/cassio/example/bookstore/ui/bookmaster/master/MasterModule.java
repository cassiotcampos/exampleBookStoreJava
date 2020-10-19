package com.cassio.example.bookstore.ui.bookmaster.master;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Cassio Ribeiro on 10/17/2020
 */
@Module
public abstract class MasterModule {


    @Provides
    static MasterContract.Presenter provideBookMasterPresenter(MasterPresenterImpl bookMasterPresenter){
        return bookMasterPresenter;
    }

    @Binds
    abstract MasterContract.View provideMainView(MasterActivity mainActivity);
}
