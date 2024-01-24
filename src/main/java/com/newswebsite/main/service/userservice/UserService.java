package com.newswebsite.main.service.userservice;

import com.newswebsite.main.dto.UserDTO;
import com.newswebsite.main.entity.User;
import com.newswebsite.main.enums.Role;
import com.newswebsite.main.exception.EmailNotFoundException;
import com.newswebsite.main.exception.InvalidUserToken;
import com.newswebsite.main.repository.RoleRepo;
import com.newswebsite.main.repository.UserRepo;
import com.newswebsite.main.service.emailservice.IEmailService;
import com.newswebsite.main.utils.EmailContentUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.UUID;

@Service
public class UserService implements IUserModificationService, IUserRetrievalService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private IEmailService emailService;

    @Autowired
    private MessageSource msg;

    private final ModelMapper mapper = new ModelMapper();

    @Override
    public void register(UserDTO newUserDTO) {
        User user = mapper.map(newUserDTO, User.class);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(newUserDTO.getPassword()));
        user.setCreatedAt(new Date());
        user.setEnabled(true);
        user.setAuthorities(Collections.singletonList(roleRepo.findByAuthority(Role.USER.name())));
        userRepo.save(user);
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
    public void changePassword(String token, String newPassword) {
        User user = userRepo.findByToken(token);
        if (user == null) throw new InvalidUserToken(msg.getMessage("user.token.invalid", null, null));
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(newPassword));
        user.setToken(null);
        userRepo.save(user);
    }

    @Override
    public boolean existsUsername(String username) {
        return userRepo.findByUsername(username) != null;
    }

    @Override
    public boolean existsEmail(String email) {
        return userRepo.findByEmail(email) != null;
    }

    @Override
    public boolean existsToken(String token) {
        return userRepo.findByToken(token) != null;
    }

    @Override
    public UserDTO findByToken(String token) {
        User user = userRepo.findByToken(token);
        if (user == null) throw new InvalidUserToken(msg.getMessage("user.token.invalid", null, null));
        user.setPassword(null);
        return mapper.map(user, UserDTO.class);
    }
}