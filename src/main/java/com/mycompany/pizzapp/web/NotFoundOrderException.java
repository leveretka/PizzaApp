package com.mycompany.pizzapp.web;

/**
 * Created by margarita on 13.08.15.
 */
public class NotFoundOrderException extends RuntimeException {
    public NotFoundOrderException(String s) {
        super(s);
    }
}
