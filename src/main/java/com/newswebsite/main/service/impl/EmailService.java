package com.newswebsite.main.service.impl;

import com.newswebsite.main.service.IEmailService;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService {
    @Override
    public void sendSimpleEmail(String to, String subject, String text) {
        Email email = EmailBuilder.startingBlank()
                .from("Toan's News", "vantoan1517@gmail.com")
                .to(to)
                .withSubject(subject)
                .withHTMLText(text)
                .buildEmail();
        Mailer mailer = MailerBuilder.withSMTPServer("smtp.gmail.com", 587, "vantoan1517@gmail.com", "kjjmdtdufyrgfsqz")
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .buildMailer();
        mailer.sendMail(email);
    }
}
