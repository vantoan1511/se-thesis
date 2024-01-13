package com.newswebsite.main.service.impl;

import com.newswebsite.main.dto.UserDTO;
import com.newswebsite.main.entity.User;
import com.newswebsite.main.repository.UserRepo;
import com.newswebsite.main.service.IUserModificationService;
import com.newswebsite.main.service.IUserRetrievalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService implements IUserModificationService, IUserRetrievalService {

    @Autowired
    private UserRepo userRepo;

    private final ModelMapper mapper = new ModelMapper();

    @Override
    public void register(UserDTO newUserDTO) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        newUserDTO.setPassword(passwordEncoder.encode(newUserDTO.getPassword()));
        newUserDTO.setCreatedAt(new Date());
        newUserDTO.setEnabled(true);
        userRepo.save(mapper.map(newUserDTO, User.class));
    }

    @Override
    public boolean existsUsername(String username) {
        return userRepo.findByUsername(username) != null;
    }

    @Override
    public boolean existsEmail(String email) {
        return userRepo.findByEmail(email) != null;
    }
}
