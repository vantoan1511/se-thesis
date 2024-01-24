package com.newswebsite.main.service.imageservice;

import com.newswebsite.main.constant.Application;
import com.newswebsite.main.dto.request.ImageRequest;
import com.newswebsite.main.dto.response.ImageResponse;
import com.newswebsite.main.entity.Image;
import com.newswebsite.main.exception.ImageNotFoundException;
import com.newswebsite.main.mapper.CollectionMapper;
import com.newswebsite.main.repository.ImageRepo;
import com.newswebsite.main.utils.SlugGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ImageWriter implements IImageWriter {

    @Autowired
    private ImageRepo imageRepo;

    @Autowired
    private ServletContext servletContext;

    private final CollectionMapper mapper = new CollectionMapper();

    @Override
    public ImageResponse handleUpload(ImageRequest fileRequest) throws IOException {
        String prefix = Application.BASE_URL;
        if (fileRequest.getFile() != null) {
            String originalFileName = fileRequest.getFile().getOriginalFilename();
            long fileSize = fileRequest.getFile().getSize();
            String fileTitle = fileRequest.getTitle().isBlank() ? originalFileName : fileRequest.getTitle();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));

            if (!fileTitle.contains(fileExtension)) {
                fileTitle += fileExtension;
            }

            String fileAlias = SlugGenerator.slugify.slugify(fileTitle);
            String directory = "resources\\images\\" + fileTitle;
            String url = prefix + "/resources/images/" + fileTitle;
            String destOnSys = servletContext.getRealPath("/") + directory;

            File destDir = new File(destOnSys);
            if (!destDir.exists()) {
                destDir.mkdirs();
            }

            fileRequest.getFile().transferTo(destDir);
            ImageResponse fileResponse = new ImageResponse();
            fileResponse.setDirectory(destOnSys);
            fileResponse.setUrl(url);
            fileResponse.setAlias(fileAlias);
            fileResponse.setSize(fileSize);
            fileResponse.setTitle(fileTitle);
            return fileResponse;
        }
        return null;
    }

    @Override
    public ImageResponse save(ImageResponse fileResponse) {
        Image image = mapper.map(fileResponse, Image.class);
        image = imageRepo.save(image);
        return mapper.map(image, ImageResponse.class);
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

}
