package com.cassio.example.bookstore.di.retrofit;

import com.cassio.example.bookstore.BuildConfig;
import com.cassio.example.bookstore.repository.ApiServices;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cassio Ribeiro on 10/17/2020
 */
@Module
public class RetrofitModule {

    @Singleton
    @Provides
    public Retrofit provideRetrofitInstance() {

        Gson gson = new GsonBuilder()
                .setDateFormat(ApiConstants.DATE_FORMAT)
                .create();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG ?
                HttpLoggingInterceptor.Level.BODY :
                HttpLoggingInterceptor.Level.NONE);


        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        return new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    public ApiServices provideApiService(Retrofit retrofit){
        return retrofit.create(ApiServices.class);
    }
}
