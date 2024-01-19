package com.newswebsite.main.exception;

public class InvalidArticleStateException extends RuntimeException {
    public InvalidArticleStateException(String message) {
        super(message);
    }
}
