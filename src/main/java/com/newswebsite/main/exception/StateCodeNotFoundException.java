package com.newswebsite.main.exception;

public class StateCodeNotFoundException extends RuntimeException {
    public StateCodeNotFoundException(String message) {
        super(message);
    }
}
