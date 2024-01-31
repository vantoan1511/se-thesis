package com.newswebsite.main.http;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class Response {
    public Date timestamp;
    public HttpStatus httpStatus;
    public String message;
}
