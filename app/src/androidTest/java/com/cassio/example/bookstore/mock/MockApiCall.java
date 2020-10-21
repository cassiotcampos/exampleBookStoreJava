package com.cassio.example.bookstore.mock;

import com.cassio.example.bookstore.di.retrofit.ApiConstants;
import com.cassio.example.bookstore.repository.ApiServices;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.vidageek.mirror.dsl.Mirror;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MockApiCall {

    @Inject Retrofit retrofit;

    private MockWebServer server;

    public void mockApiCallBefore() throws IOException {
        server = new MockWebServer();
        server.start(8080);
        setupServerUrl();
    }


    public void mockApiCallAfter()  {
        try {
            server.shutdown();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * The next X requests will be mocked with mockData instead call API
     */
    public void mockApiCallEnqueue(String mockData, int x){
        for(int i = 0; i < x; i++)
            server.enqueue(new MockResponse().setResponseCode(200).setBody(mockData));
    }

    private void setupServerUrl() {

        // original api to be replaced
        String url = server.url("/").toString();

        Gson gson = new GsonBuilder()
                .setDateFormat(ApiConstants.DATE_FORMAT)
                .create();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Object mockedApi = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
                .create(ApiServices.class);



        new Mirror()
                .on(retrofit)
                .invoke()
                .getClass()
                .cast(mockedApi);


    }

}
