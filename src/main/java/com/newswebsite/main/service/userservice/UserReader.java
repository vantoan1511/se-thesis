package com.newswebsite.main.service.userservice;

import com.newswebsite.main.dto.UserDTO;
import com.newswebsite.main.dto.response.UserProfileResponse;
import com.newswebsite.main.entity.User;
import com.newswebsite.main.exception.InvalidUserToken;
import com.newswebsite.main.exception.UserNotFoundException;
import com.newswebsite.main.mapper.CollectionMapper;
import com.newswebsite.main.mapper.UserMapper;
import com.newswebsite.main.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserReader implements IUserReader {

    private final UserRepo userRepo;

    private final MessageSource msg;

    private final CollectionMapper mapper;

    private final PasswordEncoder encoder;

    private final UserMapper userMapper;

    @Autowired
    public UserReader(UserRepo userRepo, MessageSource msg, CollectionMapper mapper, PasswordEncoder encoder, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.msg = msg;
        this.mapper = mapper;
        this.encoder = encoder;
        this.userMapper = userMapper;
    }

    @Override
    public boolean matches(String username, String password) {
        User user = userRepo.findByUsername(username);
        if (user == null) throw new UserNotFoundException("Không tìm thấy người dùng " + username);
        return encoder.matches(password, user.getPassword());
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

    @Override
    public UserDTO getUser(String username) {
        UserDTO userDTO = mapper.map(userRepo.findByUsername(username), UserDTO.class);
        userDTO.setPassword(null);
        return userDTO;
    }

    @Override
    public UserProfileResponse getUserById(long id) {
        return userMapper.toUserProfileResponse(userRepo.getOne(id));
    }

    @Override
    public UserProfileResponse getUserByUsername(String username) {
        return userMapper.toUserProfileResponse(userRepo.findByUsername(username));
    }

    @Override
    public Page<UserDTO> getAllUsers(Pageable pageable) {
        Page<UserDTO> page = userRepo.findAll(pageable).map(item -> mapper.map(item, UserDTO.class));
        page.forEach(item -> item.setPassword(null));
        return page;
    }
}
