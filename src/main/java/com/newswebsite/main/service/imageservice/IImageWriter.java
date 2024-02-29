package com.newswebsite.main.service.imageservice;

import com.newswebsite.main.dto.ImageDTO;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.util.List;

public interface IImageWriter {
    ImageDTO handleUpload(CommonsMultipartFile file) throws IOException;

    ImageDTO save(ImageDTO imageDTO);

    void delete(long id);

    void deleteAllByUsername(String username);

    void deleteMultiple(List<Long> ids);
}
