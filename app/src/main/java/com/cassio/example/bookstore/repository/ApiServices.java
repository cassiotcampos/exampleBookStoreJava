package com.cassio.example.bookstore.repository;

import com.cassio.example.bookstore.model.api.BooksMaster;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Cassio Ribeiro on 10/17/2020
 */
public interface ApiServices {

    @GET("volumes")
    Observable<BooksMaster> getBooks(
            @Query("q") String q,
            @Query("maxResults") int maxResults,
            @Query("startIndex") int startIndex,
            @Query("fields") String fields);
}
