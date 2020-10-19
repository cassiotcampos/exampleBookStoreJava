package com.cassio.example.bookstore.ui.bookmaster.master;


import com.cassio.example.bookstore.model.api.BooksMaster;
import com.cassio.example.bookstore.repository.ApiServices;
import com.cassio.example.bookstore.repository.FavoritesSharedPreferences;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Cassio Ribeiro on 10/17/2020
 */
public class BookMasterPresenterImpl implements BookMasterContract.Presenter {

    private BookMasterContract.View view;
    private ApiServices apiServices;
    private FavoritesSharedPreferences favoritesSharedPreferences;

    private int apiIndex = 0;
    private static final int maxResults = 20;

    @Inject
    public BookMasterPresenterImpl(BookMasterContract.View view,
                                   ApiServices apiServices,
                                   FavoritesSharedPreferences favoritesSharedPreferences) {

        this.apiServices = apiServices;
        this.view = view;
        this.favoritesSharedPreferences = favoritesSharedPreferences;
    }

    @Override
    public void loadBooksFromApi() {

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
                        view.showBookList(booksMaster);
                        view.hideProgress();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.hideProgress();
                        view.showApiErrorTryAgain();
                    }

                    @Override
                    public void onComplete() {
                        view.hideProgress();
                    }
                });
    }

    @Override
    public void loadMoreBooks() {
        apiIndex += maxResults;
        loadBooksFromApi();
    }

    @Override
    public boolean hasFavorites() {
        return favoritesSharedPreferences.hasFavorites();
    }
}
