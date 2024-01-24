package com.newswebsite.main.service.imageservice;

import com.newswebsite.main.dto.response.ImageResponse;
import com.newswebsite.main.entity.Image;
import com.newswebsite.main.exception.ImageNotFoundException;
import com.newswebsite.main.mapper.CollectionMapper;
import com.newswebsite.main.repository.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ImageReader implements IImageReader {

    @Autowired
    private ImageRepo imageRepo;

    private final CollectionMapper mapper = new CollectionMapper();

    @Override
    public ImageResponse getFile(String alias) {
        Image image = imageRepo.findByAlias(alias);
        if (image == null) throw new ImageNotFoundException("Tài nguyên không tồn tại");
        return mapper.map(image, ImageResponse.class);
    }

    @Override
    public Page<ImageResponse> getFiles(String author, Pageable pageable) {
        return imageRepo.findAllByCreatedBy(author, pageable)
                .map(item -> mapper.map(item, ImageResponse.class));
    }
}
