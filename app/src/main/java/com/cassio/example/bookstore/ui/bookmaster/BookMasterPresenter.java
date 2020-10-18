package com.cassio.example.bookstore.ui.bookmaster;

import com.cassio.example.bookstore.di.retrofit.ApiConstants;
import com.cassio.example.bookstore.model.api.BookDetail;
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
public class BookMasterPresenter implements IBookMaster.IPresenter {

    private IBookMaster.IMasterNavigation navigation;
    private IBookMaster.IView view;
    private ApiServices apiServices;
    private FavoritesSharedPreferences favoritesSharedPreferences;

    private int apiIndex = 0;
    private static final int maxResults = 20;

    @Inject
    public BookMasterPresenter(IBookMaster.IMasterNavigation navigation,
                               ApiServices apiServices,
                               FavoritesSharedPreferences favoritesSharedPreferences) {

        this.navigation = navigation;
        this.apiServices = apiServices;
    }

    @Override
    public void attachView(IBookMaster.IView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void loadBooks() {

        view.showProgress();

        apiServices.getBooks(ApiConstants.QUERY, maxResults, apiIndex, ApiConstants.FIELDS)
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
    public void clickBook(BookDetail book) {

    }

    @Override
    public void loadMoreBooks() {
        apiIndex += maxResults;
        loadBooks();
    }

    @Override
    public void saveInstanceStateForOrientationChanges(BooksMaster booksMaster) {

    }

    @Override
    public BooksMaster restoreInstanceStateForOrientationChanges() {
        return null;
    }
}
