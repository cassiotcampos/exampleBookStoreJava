package com.cassio.example.bookstore.application;

import android.os.AsyncTask;

import com.bumptech.glide.Glide;
import com.cassio.example.bookstore.di.AppComponent;
import com.cassio.example.bookstore.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;


/**
 * Created by Cassio Ribeiro on 10/17/2020
 */
public class BookApplication extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        // For purpose of better img tests
        Glide.get(this).clearMemory();
        new ClearGlide().execute();

    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent component = DaggerAppComponent.builder().application(this).build();
        component.inject(this);
        return component;
    }

    class ClearGlide  extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            Glide.get(BookApplication.this).clearDiskCache();
            return null;
        }
    }
}
