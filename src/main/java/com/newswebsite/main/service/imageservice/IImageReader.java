package com.newswebsite.main.service.imageservice;

import com.newswebsite.main.dto.response.ImageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IImageReader {
    ImageResponse getFile(String alias);

    Page<ImageResponse> getFiles(String author, Pageable pageable);
}
