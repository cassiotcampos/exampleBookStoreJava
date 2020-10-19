package com.cassio.example.bookstore.model.validator;

/**
 * Created by Cassio Ribeiro on 10/18/2020
 */
public abstract class BaseValidator<T> {

    protected T modelData;

    BaseValidator(T modelData) {
        this.modelData = modelData;
    }

    private BaseValidator(){}

    abstract boolean validate();
}
