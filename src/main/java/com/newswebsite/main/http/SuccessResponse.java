package com.newswebsite.main.http;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class SuccessResponse extends Response {
    private Object content;
}
