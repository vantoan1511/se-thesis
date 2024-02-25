package com.newswebsite.main.service.userservice;

import com.newswebsite.main.dto.ProfileRequest;
import com.newswebsite.main.dto.UserDTO;

import java.util.List;

public interface IUserWriter {

    void grant(String username, List<String> roles);

    void disable(String username);

    void enable(String username);

    void updateProfile(String username, ProfileRequest profile);

    void register(UserDTO newUserDTO);

    void resetPassword(String email);

    void setNewPassword(String token, String newPassword);

    void changePassword(String username, String newPassword);

    void delete(String username);

    void delete(Long id);
}
