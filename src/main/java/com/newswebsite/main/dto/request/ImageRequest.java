package com.newswebsite.main.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ImageRequest extends ContentRequest {
    private CommonsMultipartFile file;
    private String directory;
}
