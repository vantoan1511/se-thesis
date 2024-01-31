package com.newswebsite.main.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContentDTO extends BaseDTO {
    private String alias;
    @NotBlank(message = "Tiêu đề là bắt buộc")
    private String title;
    private String description;
    private Date publishedAt;
}
