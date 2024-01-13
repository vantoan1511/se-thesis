package com.newswebsite.main.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String email;
    @Column
    private String username;
    @Column
    private String password;
    @Column(name = "avatar_url")
    private String avatarUrl;
    @Column
    private String token;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(columnDefinition = "tinyint default 1")
    private boolean enabled;
}
