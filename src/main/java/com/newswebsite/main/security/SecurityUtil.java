package com.newswebsite.main.security;

import com.newswebsite.main.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

public class SecurityUtil {

    public static String username() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public static String fullname() {
        User userDTO = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDTO.getFirstName() + " " + userDTO.getLastName();
    }

    @SuppressWarnings("unchecked")
    public static List<String> getAuthorities() {
        List<String> results = new ArrayList<>();
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) (SecurityContextHolder.getContext()
                .getAuthentication().getAuthorities());
        for (GrantedAuthority authority : authorities) {
            results.add(authority.getAuthority());
        }
        return results;
    }
}
