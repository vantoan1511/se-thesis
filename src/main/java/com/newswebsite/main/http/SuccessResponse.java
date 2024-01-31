package com.newswebsite.main.http;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
public class SuccessResponse extends Response {

    public SuccessResponse(Date timestamp, HttpStatus httpStatus, String message, Object content) {
        super(timestamp, httpStatus, message);
        this.content = content;
    }

    private Object content;
}
