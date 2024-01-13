package com.newswebsite.main.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.*;
import java.util.Collection;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO implements UserDetails {
    private Long id;
    @NotEmpty(message = "Vui lòng nhập email")
    @Email(message = "Email không hợp lệ")
    private String email;
    @Size(min = 4, max = 16, message = "Tên đăng nhập có độ dài từ 4-16 ký tự")
    private String username;
    @Size(min = 8, max = 16, message = "Mật khẩu có độ dài từ 8-16 ký tự")
    private String password;
    @NotBlank(message = "Vui lòng nhập họ")
    private String firstName;
    @NotBlank(message = "Vui lòng nhập tên")
    private String lastName;
    private String avatarUrl;
    private String token;
    private Date createdAt;
    private boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
}
