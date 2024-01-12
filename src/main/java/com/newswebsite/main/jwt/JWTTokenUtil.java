package com.newswebsite.main.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JWTTokenUtil {
    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000;
    @Value("${app.jwt.secret}")
    private String SECRET_KEY;
}
