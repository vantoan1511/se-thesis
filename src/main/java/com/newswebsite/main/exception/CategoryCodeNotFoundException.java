package com.newswebsite.main.exception;

public class CategoryCodeNotFoundException extends RuntimeException {
    public CategoryCodeNotFoundException(String message) {
        super(message);
    }
}
