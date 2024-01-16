package com.newswebsite.main.service;

public interface IEmailService {
    void sendSimpleEmail(String to, String subject, String text);
}
