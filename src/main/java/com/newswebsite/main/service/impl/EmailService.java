package com.newswebsite.main.service.impl;

import com.newswebsite.main.service.IEmailService;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService {

    @Value("${email.password}")
    private String PW;
    @Value("${email.from}")
    private String from;
    @Value("${email.address}")
    private String address;
    @Value("${email.smtp.server}")
    private String smtpServer;
    @Value("${email.smtp.port}")
    private int smtpPort;

    @Override
    public void sendSimpleEmail(String to, String subject, String text) {
        Email email = EmailBuilder.startingBlank()
                .from(from, address)
                .to(to)
                .withSubject(subject)
                .withHTMLText(text)
                .buildEmail();
        Mailer mailer = MailerBuilder.withSMTPServer(smtpServer, smtpPort, address, PW)
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .buildMailer();
        mailer.sendMail(email);
    }
}
