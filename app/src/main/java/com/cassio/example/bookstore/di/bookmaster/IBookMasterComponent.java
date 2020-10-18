package com.cassio.example.bookstore.di.bookmaster;

import com.cassio.example.bookstore.di.application.IApplicationComponent;
import com.cassio.example.bookstore.di.bookdetail.BookDetailModule;
import com.cassio.example.bookstore.di.bookdetail.IBookDetailComponent;
import com.cassio.example.bookstore.di.scope.ActivityScope;
import com.cassio.example.bookstore.ui.bookmaster.BookMasterActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = {IApplicationComponent.class},
        modules = {BookMasterModule.class})
public interface IBookMasterComponent {

    void inject(BookMasterActivity activity);
    IBookDetailComponent setModule(BookDetailModule bookDetailModule);


}