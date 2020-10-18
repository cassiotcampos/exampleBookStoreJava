package com.cassio.example.bookstore.ui.base;

/**
 * Created by Cassio Ribeiro on 10/17/2020
 */
public interface IBasePresenter<T> {
    void attachView(T view);
    void detachView();
}