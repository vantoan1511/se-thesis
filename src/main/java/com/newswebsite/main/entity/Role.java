package com.newswebsite.main.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "role")
@Getter
@Setter
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "authority", unique = true, nullable = false)
    private String authority;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "authorities")
    private List<User> users = new ArrayList<>();

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
