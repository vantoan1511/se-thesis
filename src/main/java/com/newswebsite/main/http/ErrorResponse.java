package com.newswebsite.main.http;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Getter
public class ErrorResponse extends Response {
    public ErrorResponse(Date timestamp, HttpStatus httpStatus, String message, List<String> errors) {
        super(timestamp, httpStatus, message);
        this.errors = errors;
    }

    public ErrorResponse(Date timestamp, HttpStatus httpStatus, String message, String error) {
        super(timestamp, httpStatus, message);
        this.errors = Collections.singletonList(error);
    }

    private List<String> errors;
}
