package com.cassio.example.bookstore.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Cassio Ribeiro on 10/17/2020
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface FragmentScope {
}
