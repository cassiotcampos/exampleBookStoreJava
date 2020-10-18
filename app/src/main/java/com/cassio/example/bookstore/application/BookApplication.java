package com.cassio.example.bookstore.application;

import android.app.Application;
import android.content.Context;

import com.cassio.example.bookstore.di.application.ApplicationModule;
import com.cassio.example.bookstore.di.application.DaggerIApplicationComponent;
import com.cassio.example.bookstore.di.application.IApplicationComponent;


/**
 * Created by Cassio Ribeiro on 10/17/2020
 */
public class BookApplication extends Application {

    private IApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerIApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

    }

    public static IApplicationComponent getComponent(Context context) {
        return ((BookApplication) context.getApplicationContext()).applicationComponent;
    }
}
