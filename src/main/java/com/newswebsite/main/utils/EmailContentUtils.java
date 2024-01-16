package com.newswebsite.main.utils;

import com.newswebsite.main.constant.Application;

public class EmailContentUtils {
    public static String resetEmail(String to, String token) {
        return String.format(
                "<p>Xin chào, %s</p>" +
                        "<p>Click vào <a href='%s/reset?token=%s'>đây</a> để reset mật khẩu!</p>" +
                        "<p>Nếu không phải bạn thì hãy bỏ qua meo này!</p>" +
                        "<p>Trân trọng!</p>",
                to, Application.BASE_URL, token);
    }
}
