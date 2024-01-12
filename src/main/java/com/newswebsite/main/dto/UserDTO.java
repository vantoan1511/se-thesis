package com.newswebsite.main.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserDTO {
    private Long id;
    private String email;
    private String username;
    private String password;
    private String avatarUrl;
    private String token;
    private boolean enabled;
}
