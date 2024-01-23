package com.newswebsite.main.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContentRequest {
    private Long id;
    private String alias;
    @NotBlank(message = "Tiêu đề là bắt buộc")
    private String title;
    private String description;
}
