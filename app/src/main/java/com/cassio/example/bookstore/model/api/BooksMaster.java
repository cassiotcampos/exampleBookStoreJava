
package com.cassio.example.bookstore.model.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BooksMaster {

    @SerializedName("kind")
    private String kind;

    @SerializedName("totalItems")
    private Integer totalItems;

    @SerializedName("items")
    private List<BookDetail> books = null;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public List<BookDetail> getBooks() {
        return books;
    }

    public void setBooks(List<BookDetail> books) {
        this.books = books;
    }
}
