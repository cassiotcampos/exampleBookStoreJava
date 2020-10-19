package com.cassio.example.bookstore.repository;

import android.content.Context;
import android.content.SharedPreferences;

import com.cassio.example.bookstore.model.api.BookDetail;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * Created by Cassio Ribeiro on 10/17/2020
 */
public class FavoritesSharedPreferences {

    public static final String PREF_KEY = "BOOKS_PREF";
    public static final String FAV_KEY = "FAVORITES";

    private Context context;
    private SharedPreferences sharedPreferences;
    private ArrayList<BookDetail> favoriteBooks;

    public FavoritesSharedPreferences(Context context) {
        this.context = context;
    }

    private SharedPreferences getPrefs() {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }

    public boolean isFavorite(BookDetail bookDetail) {
        for(BookDetail b : getFavoriteBooks()){
            if(b.getId() != null){
                if(b.getId().equals(bookDetail.getId())){
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<BookDetail> getFavoriteBooks() {

        if (favoriteBooks == null) {

            String favStr = getPrefs().getString(FAV_KEY, null);
            if (favStr != null) {

                favoriteBooks = new Gson().fromJson(favStr, new TypeToken<ArrayList<BookDetail>>() {
                }.getType());

                if (favoriteBooks != null) {
                    return favoriteBooks;
                }
            }

            favoriteBooks = new ArrayList<>();
        }
        return favoriteBooks;
    }

    public void addToFavorites(BookDetail bookDetail) {
        for(BookDetail mBook : getFavoriteBooks()){
            if(mBook.getId().equals(bookDetail.getId())){
                return;
            }
        }

        favoriteBooks.add(bookDetail);
        save();
    }

    private void save() {
        sharedPreferences.edit()
                .putString(FAV_KEY, new Gson()
                        .toJson(favoriteBooks)).apply();
    }

    public void removeFavorite(BookDetail bookDetail) {

        BookDetail itemToRemove = null;
        for(BookDetail mBook : getFavoriteBooks()){
            if (mBook.getId().equals(bookDetail.getId())) {
                itemToRemove = mBook;
                break;
            }
        }

        if(itemToRemove != null){
            favoriteBooks.remove(itemToRemove);
            save();
        }
    }

    public boolean hasFavorites() {
        return getFavoriteBooks() != null && !getFavoriteBooks().isEmpty();
    }
}