package com.newswebsite.main.service.userservice;

import com.newswebsite.main.dto.ProfileRequest;
import com.newswebsite.main.dto.UserDTO;

public interface IUserWriter {
    void updateProfile(String username, ProfileRequest profile);

    void register(UserDTO newUserDTO);

    void resetPassword(String email);

    void setNewPassword(String token, String newPassword);

    void changePassword(String username, String newPassword);
}
