package com.newswebsite.main.exception;

public class InvalidArticleOperationException extends RuntimeException {
    public InvalidArticleOperationException(String message) {
        super(message);
    }
}
