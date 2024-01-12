package com.newswebsite.main.http;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@SuperBuilder
public class Response {
    public Date timestamp;
    public  int statusCode;
    public String message;
}
