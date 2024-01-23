package com.newswebsite.main.service.impl;

import com.newswebsite.main.constant.Application;
import com.newswebsite.main.dto.request.FileRequest;
import com.newswebsite.main.dto.response.FileResponse;
import com.newswebsite.main.mapper.CollectionMapper;
import com.newswebsite.main.repository.FileRepo;
import com.newswebsite.main.service.IFileWriter;
import com.newswebsite.main.utils.SlugGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;

@Service
public class FileWriter implements IFileWriter {

    @Autowired
    private FileRepo fileRepo;

    @Autowired
    private ServletContext servletContext;

    private final CollectionMapper mapper = new CollectionMapper();

    @Override
    public FileResponse handleUpload(FileRequest fileRequest) throws IOException {
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
            FileResponse fileResponse = new FileResponse();
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
    public FileResponse save(FileResponse fileResponse) {
        com.newswebsite.main.entity.File file = mapper.map(fileResponse, com.newswebsite.main.entity.File.class);
        file = fileRepo.save(file);
        return mapper.map(file, FileResponse.class);
    }

    @Override
    public void delete(long id) {

    }
}
