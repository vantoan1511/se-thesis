package com.newswebsite.main.service.imageservice;

import com.newswebsite.main.dto.ImageDTO;
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

    private final ImageRepo imageRepo;

    private final CollectionMapper mapper = new CollectionMapper();

    @Autowired
    public ImageReader(ImageRepo imageRepo) {
        this.imageRepo = imageRepo;
    }

    @Override
    public ImageDTO getFile(String alias) {
        Image image = imageRepo.findByAlias(alias);
        if (image == null) throw new ImageNotFoundException("Tài nguyên không tồn tại");
        return mapper.map(image, ImageDTO.class);
    }

    @Override
    public Page<ImageDTO> getFiles(String author, Pageable pageable) {
        return imageRepo.findAllByCreatedBy(author, pageable)
                .map(item -> mapper.map(item, ImageDTO.class));
    }
}
