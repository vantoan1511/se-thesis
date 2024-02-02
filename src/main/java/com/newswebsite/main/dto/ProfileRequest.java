package com.newswebsite.main.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ProfileRequest {
    @NotBlank(message = "Họ là bắt buộc")
    private String firstName;
    @NotBlank(message = "Tên là bắt buộc")
    private String lastName;
    @NotBlank(message = "Email là bắt buộc")
    @Email(message = "Email không hợp lệ")
    private String email;
}
