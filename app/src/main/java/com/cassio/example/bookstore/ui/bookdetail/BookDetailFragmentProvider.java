package com.cassio.example.bookstore.ui.bookdetail;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by mertsimsek on 02/06/2017.
 */
@Module
public abstract class BookDetailFragmentProvider {

    @ContributesAndroidInjector(modules = BookDetailModule.class)
    abstract BookDetailFragment provideBookDetailFragmentFactory();
}
