package com.newswebsite.main.exception;

public class InvalidUserToken extends RuntimeException {
    public InvalidUserToken(String message) {
        super(message);
    }
}
