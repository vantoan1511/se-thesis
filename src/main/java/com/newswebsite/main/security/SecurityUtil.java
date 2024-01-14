package com.newswebsite.main.security;

import com.newswebsite.main.dto.UserDTO;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    public static String username;
    public static String fullName;

    static {
        UserDTO loggedInUser = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        username = loggedInUser.getUsername();
        fullName = loggedInUser.getFirstName().concat(" ").concat(loggedInUser.getLastName());
    }
}
