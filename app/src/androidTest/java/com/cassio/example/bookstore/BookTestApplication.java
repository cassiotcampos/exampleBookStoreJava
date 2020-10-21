package com.cassio.example.bookstore;

import android.os.AsyncTask;

import com.bumptech.glide.Glide;
import com.cassio.example.bookstore.application.BookApplication;
import com.cassio.example.bookstore.di.AppComponent;
import com.cassio.example.bookstore.di.DaggerAppComponent;
import com.cassio.example.bookstore.di.TestRetrofitModule;
import com.cassio.example.bookstore.di.retrofit.RetrofitModule;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;


/**
 * Created by Cassio Ribeiro on 10/17/2020
 */
public class BookTestApplication extends BookApplication {

    public static RetrofitModule retrofitTestModule;

    @Override
    public void onCreate() {
        super.onCreate();
        // For purpose of better img tests
        Glide.get(this).clearMemory();
        new ClearGlide().execute();

    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent component = DaggerAppComponent.builder()
                .setRetrofitModule(new TestRetrofitModule())
                .application(this).build();
        return component;
    }

    class ClearGlide  extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            Glide.get(BookTestApplication.this).clearDiskCache();
            return null;
        }
    }
}
