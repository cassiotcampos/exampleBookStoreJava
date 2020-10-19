package com.cassio.example.bookstore.model.validator;

import com.cassio.example.bookstore.model.api.BooksMaster;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by Cassio Ribeiro on 10/17/2020
 */
public class BooksMasterValidator extends BaseValidator<BooksMaster>{

    public BooksMasterValidator(BooksMaster modelData) {
        super(modelData);
    }

    public boolean isBookListAvailable() {
        if (
                modelData != null
                        && modelData.getBooks() != null
                        && !modelData.getBooks().isEmpty()) {

            return true;

        }else{
            return false;
        }
    }


    @Override
    public boolean validate() {
        return isBookListAvailable();
    }

    public void initBooksWithEmptyList() {
        if(modelData != null && modelData.getBooks() == null){
            modelData.setBooks(new ArrayList<>());
        }
    }

    public String getAsJson() {
        return new Gson().toJson(modelData);
    }
}
