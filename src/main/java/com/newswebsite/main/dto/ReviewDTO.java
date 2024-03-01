package com.newswebsite.main.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewDTO extends BaseDTO {

    @NotBlank(message = "Mục này là bắt buộc")
    private String text;
    @NotNull(message = "Mục này là bắt buộc")
    private Long userId;
    private String userFirstName;
    private String userLastName;
    @NotNull(message = "Mục này là bắt buộc")
    private Long articleId;
    private String articleAlias;
    private Long parentId;
    private String parentText;
}
