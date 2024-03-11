package com.newswebsite.main.service.userservice;

import com.newswebsite.main.entity.User;
import com.newswebsite.main.repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    public CustomUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = isEmail(username) ? userRepo.findByEmail(username) : userRepo.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("Không tìm thấy người dùng với username/email [%s]".formatted(username));
        return user;
    }

    private boolean isEmail(String text) {
        return text.contains("@");
    }
}
