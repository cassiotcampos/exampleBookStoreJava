package com.cassio.example.bookstore.ui.bookmaster.master;


import android.content.Intent;
import android.os.Bundle;

import com.cassio.example.bookstore.model.api.BooksMaster;
import com.cassio.example.bookstore.model.validator.BooksMasterValidator;
import com.cassio.example.bookstore.repository.ApiServices;
import com.cassio.example.bookstore.repository.FavoritesSharedPreferences;
import com.google.gson.Gson;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Cassio Ribeiro on 10/17/2020
 */
public class MasterPresenterImpl implements MasterContract.Presenter {

    private static final String ARG_BOOK_ADAPTER_DATA = "ARG_BOOK_ADAPTER_DATA";
    private static final String ARG_BOOK_API_INDEX = "ARG_BOOK_API_INDEX";
    private static final int ARG_API_INDEX_EMPTY = -1;

    private MasterContract.View view;
    private ApiServices apiServices;
    private FavoritesSharedPreferences favoritesSharedPreferences;
    private BooksMaster booksLoaded;

    private int apiIndex = 0;
    private static final int maxResults = 20;

    @Inject
    public MasterPresenterImpl(MasterContract.View view,
                               ApiServices apiServices,
                               FavoritesSharedPreferences favoritesSharedPreferences) {

        this.apiServices = apiServices;
        this.view = view;
        this.favoritesSharedPreferences = favoritesSharedPreferences;
    }

    @Override
    public void loadBooksOnCreate(Intent mIntent) {

        if(loadFromIntent(mIntent)){
            view.showBookList(booksLoaded);
            view.hideProgress();
        } else {
            loadFromApi();
        }
    }

    private void loadFromApi() {

        view.showProgress();

        apiServices.getBooks("Android", maxResults, apiIndex, "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BooksMaster>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull BooksMaster booksMaster) {
                        booksLoaded = booksMaster;
                        apiIndex += maxResults;
                        view.showBookList(booksMaster);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.showApiErrorTryAgain();
                    }

                    @Override
                    public void onComplete() {
                        view.hideProgress();
                    }
                });
    }

    private boolean loadFromIntent(Intent mIntent) {
        if (mIntent != null) {

            BooksMaster booksMaster = new BooksMaster();

            // recover from savedInstanceState
            if (mIntent.getExtras() != null) {
                String recoveredBookMasterStr = mIntent.getExtras()
                        .getString(ARG_BOOK_ADAPTER_DATA);

                int recoveredApiIndex = mIntent.
                        getIntExtra(ARG_BOOK_API_INDEX, ARG_API_INDEX_EMPTY);

                BooksMaster recoveredBookMaster = new Gson().fromJson(recoveredBookMasterStr, BooksMaster.class);
                if (new BooksMasterValidator(recoveredBookMaster).isBookListAvailable()
                        && recoveredApiIndex != ARG_API_INDEX_EMPTY) {

                    booksLoaded = recoveredBookMaster;
                    apiIndex = recoveredApiIndex;

                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void loadMoreBooks() {
        loadFromApi();
    }

    @Override
    public boolean hasFavorites() {
        return favoritesSharedPreferences.hasFavorites();
    }

    @Override
    public void saveState(Bundle outState) {
        outState.putString(ARG_BOOK_ADAPTER_DATA, new BooksMasterValidator(booksLoaded).getAsJson());
        outState.putInt(ARG_BOOK_API_INDEX, apiIndex);
    }
}
