package com.newswebsite.main.service.emailservice;

public interface IEmailService {
    void sendSimpleEmail(String to, String subject, String text);
}
