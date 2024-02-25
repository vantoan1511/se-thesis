package com.newswebsite.main.service.userservice;

import com.newswebsite.main.dto.RoleDTO;
import com.newswebsite.main.dto.UserDTO;
import com.newswebsite.main.entity.User;
import com.newswebsite.main.mapper.CollectionMapper;
import com.newswebsite.main.repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    private final CollectionMapper mapper;

    public CustomUserDetailsService(UserRepo userRepo, CollectionMapper mapper) {
        this.userRepo = userRepo;
        this.mapper = mapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = isEmail(username) ? userRepo.findByEmail(username) : userRepo.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException("Không tìm thấy người dùng với thông tin " + username);
        List<RoleDTO> authorities = new ArrayList<>(mapper.map(user.getAuthorities(), RoleDTO.class));
        UserDTO userDTO = mapper.map(user, UserDTO.class);
        userDTO.setAuthorities(authorities);
        return userDTO;
    }

    private boolean isEmail(String text) {
        return text.contains("@");
    }
}
