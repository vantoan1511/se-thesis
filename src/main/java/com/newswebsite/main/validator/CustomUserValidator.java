package com.newswebsite.main.validator;

import com.newswebsite.main.dto.UserDTO;
import com.newswebsite.main.service.userservice.IUserReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CustomUserValidator implements Validator {

    private final IUserReader userRetrievalService;

    @Autowired
    public CustomUserValidator(IUserReader userRetrievalService) {
        this.userRetrievalService = userRetrievalService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDTO userDTO = (UserDTO) target;
        if (userRetrievalService.existsEmail(userDTO.getEmail())) {
            errors.rejectValue("email", "email.exists", "Email đã liên kết với một tài khoản khác");
        }
        if (userRetrievalService.existsUsername(userDTO.getUsername())) {
            errors.rejectValue("username", "username.exists", "Tên đăng nhập đã tồn tại");
        }
    }
}
