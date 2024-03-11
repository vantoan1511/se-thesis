package com.newswebsite.main.service.userservice;

import com.newswebsite.main.dto.UserDTO;
import com.newswebsite.main.dto.request.UserRegistrationRequest;
import com.newswebsite.main.entity.User;
import com.newswebsite.main.enums.Role;
import com.newswebsite.main.exception.EmailExistedException;
import com.newswebsite.main.exception.EmailNotFoundException;
import com.newswebsite.main.exception.InvalidUserToken;
import com.newswebsite.main.exception.UserNotFoundException;
import com.newswebsite.main.mapper.UserMapper;
import com.newswebsite.main.repository.RoleRepo;
import com.newswebsite.main.repository.UserRepo;
import com.newswebsite.main.service.emailservice.IEmailService;
import com.newswebsite.main.service.imageservice.IImageWriter;
import com.newswebsite.main.utils.EmailContentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserWriter implements IUserWriter {

    private final UserRepo userRepo;

    private final RoleRepo roleRepo;

    private final IImageWriter imageWriter;

    private final MessageSource msg;

    private final IEmailService emailService;

    private final UserMapper userMapper;

    @Autowired
    public UserWriter(
            UserRepo userRepo,
            RoleRepo roleRepo,
            IImageWriter imageWriter,
            MessageSource msg,
            IEmailService emailService,
            UserMapper userMapper
    ) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.imageWriter = imageWriter;
        this.msg = msg;
        this.emailService = emailService;
        this.userMapper = userMapper;
    }

    @Override
    public void grant(String username, List<String> roles) {
        User user = userRepo.findByUsername(username);
        if (user == null) throw new UserNotFoundException("Không tìm thấy người dùng ");
        if (roles.isEmpty()) throw new RuntimeException("Cần tối thiểu 1 vai trò");
        List<com.newswebsite.main.entity.Role> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(roleRepo.findByAuthority(role));
        }
        user.setAuthorities(authorities);
        userRepo.save(user);
    }

    @Override
    public void disable(String username) {
        User user = userRepo.findByUsername(username);
        if (user == null) throw new UserNotFoundException("Không tìm thấy người dùng");
        user.setEnabled(false);
        userRepo.save(user);
    }

    @Override
    public void enable(String username) {
        User user = userRepo.findByUsername(username);
        if (user == null) throw new UserNotFoundException("Không tìm thấy người dùng");
        user.setEnabled(true);
        userRepo.save(user);
    }

    @Override
    public void updateProfile(UserDTO userDTO) {
        User user = userRepo.findByUsername(userDTO.getUsername());
        if (user == null || !user.isEnabled()) throw new UserNotFoundException("Người dùng không tồn tại");
        //update email
        User anotherUser = userRepo.findByEmail(userDTO.getEmail());
        if (anotherUser != null && !anotherUser.getUsername().equals(userDTO.getUsername()))
            throw new EmailExistedException("Địa chỉ email đã liên kết với một tài khoản khác");
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setAvatarUrl(userDTO.getAvatarUrl());
        userRepo.save(user);
    }

    @Override
    public void register(UserRegistrationRequest newUserRequest) {
        User user = userMapper.toUser(newUserRequest);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(new Date());
        user.setEnabled(true);
        user.setAvatarUrl("/static/public/images/avatar.png");
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
    public void setNewPassword(String token, String newPassword) {
        User user = userRepo.findByToken(token);
        if (user == null) throw new InvalidUserToken(msg.getMessage("user.token.invalid", null, null));
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(newPassword));
        user.setToken(null);
        userRepo.save(user);
    }

    @Override
    public void changePassword(String username, String newPassword) {
        User user = userRepo.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException("Không tìm thấy người dùng " + username);
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(newPassword));
        userRepo.save(user);
    }

    @Override
    public void delete(String username) {
        User user = userRepo.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException("Không tìm thấy người dùng " + username);
        userRepo.delete(user);
        imageWriter.deleteAllByUsername(username);
    }

    @Override
    public void delete(Long id) {
        if (!userRepo.exists(id))
            throw new UserNotFoundException(String.format("Tài khoản người dùng với id %s không tồn tại", id));
        userRepo.delete(id);
    }
}
