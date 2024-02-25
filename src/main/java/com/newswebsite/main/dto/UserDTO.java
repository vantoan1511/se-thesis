package com.newswebsite.main.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO implements UserDetails, CredentialsContainer {

    private Long id;
    private String email;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String avatarUrl;
    private String token;
    private Date createdAt;
    private boolean enabled;
    private List<RoleDTO> authorities;

    public String fullName() {
        return firstName.concat(" ").concat(lastName);
    }

    public List<String> getRoles() {
        return authorities.stream().map(RoleDTO::getAuthority).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void eraseCredentials() {
        this.password = null;
    }
}
