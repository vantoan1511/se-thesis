package com.newswebsite.main.service.impl;

import com.newswebsite.main.dto.UserDTO;
import com.newswebsite.main.entity.User;
import com.newswebsite.main.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    private final ModelMapper mapper = new ModelMapper();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = isEmail(username) ? userRepo.findByEmail(username) : userRepo.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException("Không tìm thấy người dùng với thông tin " + username);
        return mapper.map(user, UserDTO.class);
    }

    private boolean isEmail(String text) {
        return text.contains("@");
    }
}
