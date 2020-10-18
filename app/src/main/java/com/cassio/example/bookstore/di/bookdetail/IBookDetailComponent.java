package com.cassio.example.bookstore.di.bookdetail;

import com.cassio.example.bookstore.di.scope.FragmentScope;
import com.cassio.example.bookstore.ui.bookdetail.BookDetailFragment;

import dagger.Subcomponent;

/**
 * Created by Cassio Ribeiro on 10/17/2020
 */
@FragmentScope
@Subcomponent(modules = {BookDetailModule.class})
public interface IBookDetailComponent {

    void inject(BookDetailFragment bookDetailFragment);
}
