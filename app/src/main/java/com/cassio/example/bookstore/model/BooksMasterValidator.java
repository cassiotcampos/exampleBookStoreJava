package com.cassio.example.bookstore.model;

import com.cassio.example.bookstore.model.api.BookDetail;
import com.cassio.example.bookstore.model.api.BooksMaster;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by Cassio Ribeiro on 10/17/2020
 */
public interface BooksMasterValidator {

    static boolean isTitleAvailable(BookDetail bookDetail) {
        if (
                bookDetail != null
                        && bookDetail.getVolumeInfo() != null
                        && bookDetail.getVolumeInfo().getTitle() != null
                        && !bookDetail.getVolumeInfo().getTitle().isEmpty()) {

            return true;

        }else{
            return false;
        }
    }

    static boolean isBookListAvailable(BooksMaster booksMaster) {
        if (
                booksMaster != null
                        && booksMaster.getBooks() != null
                        && !booksMaster.getBooks().isEmpty()) {

            return true;

        }else{
            return false;
        }
    }

    static boolean getDescription(BooksMaster booksMaster) {
        if (
                booksMaster != null
                        && booksMaster.getBooks() != null
                        && !booksMaster.getBooks().isEmpty()) {

            return true;

        }else{
            return false;
        }
    }

    static boolean validate(BooksMaster booksMaster) {
        return isBookListAvailable(booksMaster);
    }

    static void initBooksWithEmptyList(BooksMaster booksMaster) {
        if(booksMaster != null && booksMaster.getBooks() == null){
            booksMaster.setBooks(new ArrayList<>());
        }
    }

    static String getAsJson(BooksMaster bookMaster) {
        return new Gson().toJson(bookMaster);
    }
}
