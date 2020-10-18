package com.cassio.example.bookstore.ui.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cassio.example.bookstore.application.BookApplication;
import com.cassio.example.bookstore.di.application.IApplicationComponent;

/**
 * Created by Cassio Ribeiro on 10/17/2020
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent(BookApplication.getComponent(this));
    }

    protected abstract void setupActivityComponent(IApplicationComponent applicationComponent);
}
