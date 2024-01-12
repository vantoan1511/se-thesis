package com.newswebsite.main.http;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ErrorResponse extends Response {
    private String error;
}
