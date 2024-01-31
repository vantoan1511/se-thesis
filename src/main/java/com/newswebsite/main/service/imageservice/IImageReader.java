package com.newswebsite.main.service.imageservice;

import com.newswebsite.main.dto.ImageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IImageReader {
    ImageDTO getFile(String alias);

    Page<ImageDTO> getFiles(String author, Pageable pageable);
}
