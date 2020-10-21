package com.cassio.example.bookstore.di;

import android.os.AsyncTask;

import com.cassio.example.bookstore.BuildConfig;
import com.cassio.example.bookstore.di.retrofit.ApiConstants;
import com.cassio.example.bookstore.di.retrofit.RetrofitModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cassio Ribeiro on 10/17/2020
 */
public class TestRetrofitModule extends RetrofitModule {

    private static MockWebServer server;

    private static Retrofit retrofit;

    @Override
    public Retrofit provideRetrofitInstance()  {

        Gson gson = new GsonBuilder()
                .setDateFormat(ApiConstants.DATE_FORMAT)
                .create();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        return new Retrofit.Builder()
                .baseUrl("http://localhost:8080/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        // new SetupMock().execute();

        /*try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        // return retrofit;
    }

    class SetupMock extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {

            Gson gson = new GsonBuilder()
                    .setDateFormat(ApiConstants.DATE_FORMAT)
                    .create();

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(BuildConfig.DEBUG ?
                    HttpLoggingInterceptor.Level.BODY :
                    HttpLoggingInterceptor.Level.NONE);

            /*server = new MockWebServer();
            try {
                server.start(8080);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String url = server.url("/").toString();*/

            OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            // server.enqueue(new MockResponse().setResponseCode(200).setBody(MockData.GET_VOLUMES_SUCCESS));

            retrofit = new Retrofit.Builder()
                    .baseUrl("http://localhost:8080/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();

            /*try {
                server.shutdown();
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            return null;
        }
    }
}
