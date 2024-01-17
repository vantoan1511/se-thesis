package com.newswebsite.main.security;

import com.newswebsite.main.dto.UserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

public class SecurityUtil {
    public static String username;
    public static String fullName;

    static {
        UserDTO loggedInUser = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        username = loggedInUser.getUsername();
        fullName = loggedInUser.getFirstName().concat(" ").concat(loggedInUser.getLastName());
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
