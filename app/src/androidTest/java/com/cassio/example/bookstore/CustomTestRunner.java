package com.cassio.example.bookstore;

import android.app.Application;
import android.content.Context;

import androidx.test.runner.AndroidJUnitRunner;

/**
 * Created by Cassio Ribeiro on 10/21/2020
 */
public class CustomTestRunner extends AndroidJUnitRunner {

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.newApplication(cl, BookTestApplication.class.getName(), context);
    }
}