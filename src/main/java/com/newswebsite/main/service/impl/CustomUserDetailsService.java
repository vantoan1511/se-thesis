package com.newswebsite.main.service.impl;

import com.newswebsite.main.dto.UserDTO;
import com.newswebsite.main.entity.User;
import com.newswebsite.main.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = isEmail(username) ? userRepo.findByEmail(username) : userRepo.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException("Không tìm thấy người dùng với thông tin " + username);
        return UserDTO.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .enabled(true)
                .build();
    }

    private boolean isEmail(String text) {
        return text.contains("@");
    }
}
