package com.newswebsite.main.service.imageservice;

import com.newswebsite.main.constant.Application;
import com.newswebsite.main.dto.ImageDTO;
import com.newswebsite.main.dto.request.ImageRequest;
import com.newswebsite.main.dto.response.ImageResponse;
import com.newswebsite.main.entity.Image;
import com.newswebsite.main.exception.ImageNotFoundException;
import com.newswebsite.main.mapper.CollectionMapper;
import com.newswebsite.main.repository.ImageRepo;
import com.newswebsite.main.utils.SlugGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class ImageWriter implements IImageWriter {

    @Autowired
    private ImageRepo imageRepo;

    @Autowired
    private ServletContext servletContext;

    private final CollectionMapper mapper = new CollectionMapper();

    @Override
    public ImageDTO handleUpload(CommonsMultipartFile file) throws IOException {
        if (file != null) {
            String originalFileName = file.getOriginalFilename();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            String fileName = SlugGenerator.generateUniqueSlug("uploaded-image").concat(fileExtension);

            String directory = servletContext.getRealPath("/") + "resources\\images\\" + fileName;

            File destDir = new File(directory);
            if (!destDir.exists() && destDir.mkdirs()) {
                file.transferTo(destDir);
                return new ImageDTO(file.getSize(), Application.BASE_URL.concat("/resources/images/").concat(fileName), directory);
            }
        }
        return null;
    }

    @Override
    public ImageDTO save(ImageDTO imageDTO) {
        Image image = mapper.map(imageDTO, Image.class);
        image.setPublishedAt(new Date());
        Image oldImage = new Image();
        if (imageDTO.getId() != null) {
            oldImage = imageRepo.findOne(imageDTO.getId());
            if (oldImage == null) throw new ImageNotFoundException("Tập tin không tồn tại");
            image.setSize(oldImage.getSize());
            image.setDirectory(oldImage.getDirectory());
            image.setUrl(oldImage.getUrl());
            image.setCreatedAt(oldImage.getCreatedAt());
            image.setCreatedBy(oldImage.getCreatedBy());
            image.setPublishedAt(oldImage.getPublishedAt());
        }

        String newAlias = StringUtils.hasText(imageDTO.getAlias()) ? imageDTO.getAlias() : imageDTO.getTitle();
        newAlias = SlugGenerator.slugify.slugify(newAlias);
        String oldAlias = oldImage.getAlias();
        if (!newAlias.equals(oldAlias) && !isUniqueAlias(newAlias)) {
            newAlias = SlugGenerator.generateUniqueSlug(newAlias);
        }

        image.setAlias(newAlias);
        image = imageRepo.save(image);
        return mapper.map(image, ImageDTO.class);
    }

    @Override
    @Transactional
    public void delete(long id) {
        Image image = imageRepo.findOne(id);
        if (image == null) throw new ImageNotFoundException("Hình ảnh không tồn tại ");
        File file = new File(image.getDirectory());
        if (file.exists() && file.delete()) {
            imageRepo.delete(id);
        }
    }

    @Override
    @Transactional
    public void deleteMultiple(List<Long> ids) {
        for (long id : ids) {
            delete(id);
        }
    }

    private boolean isUniqueAlias(String alias) {
        return imageRepo.findByAlias(alias) == null;
    }
}
