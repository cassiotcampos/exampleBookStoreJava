package com.cassio.example.bookstore.di.retrofit;

/**
 * Created by Cassio Ribeiro on 10/17/2020
 */
public class ApiConstants {
    public static final String BASE_URL = "https://www.googleapis.com/books/v1/";
    public static final String FIELDS = "kind,totalItems,items(id,volumeInfo/title,volumeInfo/authors,volumeInfo/publisher,volumeInfo/publishedDate,volumeInfo/description,volumeInfo/imageLinks(smallThumbnail),searchInfo(textSnippet),saleInfo/buyLink)";
    public static final String DATE_FORMAT = "yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SS'Z'";
    public static final String QUERY = "Android";
}
