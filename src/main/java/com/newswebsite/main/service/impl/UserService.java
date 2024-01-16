package com.newswebsite.main.service.impl;

import com.newswebsite.main.dto.UserDTO;
import com.newswebsite.main.entity.User;
import com.newswebsite.main.exception.EmailNotFoundException;
import com.newswebsite.main.repository.UserRepo;
import com.newswebsite.main.service.IEmailService;
import com.newswebsite.main.service.IUserModificationService;
import com.newswebsite.main.service.IUserRetrievalService;
import com.newswebsite.main.utils.EmailContentUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class UserService implements IUserModificationService, IUserRetrievalService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private IEmailService emailService;

    @Autowired
    private MessageSource msg;

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
    public void resetPassword(String email) {
        User user = userRepo.findByEmail(email);
        if (user == null)
            throw new EmailNotFoundException(msg.getMessage("email.not.found", null, null) + email);
        String token = UUID.randomUUID().toString();
        user.setToken(token);
        userRepo.save(user);

        String text = EmailContentUtils.resetEmail(user.getUsername(), token);
        emailService.sendSimpleEmail(email, "Reset mật khẩu", text);
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
