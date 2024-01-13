package com.newswebsite.main.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FlashMessage {

    public static final String SUCCESS = "success";
    public static final String DANGER = "danger";
    public static final String WARNING = "warning";
    public static final String INFO = "info";

    private String type;
    private String message;

    public static FlashMessage success(String message) {
        return new FlashMessage(FlashMessage.SUCCESS, message);
    }

    public static FlashMessage danger(String message) {
        return new FlashMessage(FlashMessage.DANGER, message);
    }

    public static FlashMessage warning(String message) {
        return new FlashMessage(FlashMessage.WARNING, message);
    }

    public static FlashMessage info(String message) {
        return new FlashMessage(FlashMessage.INFO, message);
    }
}
