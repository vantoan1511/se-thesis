package com.newswebsite.main.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
public class RoleDTO implements GrantedAuthority {
    private long id;
    private String authority;
    private String description;
}
