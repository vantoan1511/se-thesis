package com.newswebsite.main.service.fileservice;

import com.newswebsite.main.dto.response.FileResponse;
import com.newswebsite.main.entity.File;
import com.newswebsite.main.exception.FileNotFoundException;
import com.newswebsite.main.mapper.CollectionMapper;
import com.newswebsite.main.repository.FileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FileReader implements IFileReader {

    @Autowired
    private FileRepo fileRepo;

    private final CollectionMapper mapper = new CollectionMapper();

    @Override
    public FileResponse getFile(String alias) {
        File file = fileRepo.findByAlias(alias);
        if (file == null) throw new FileNotFoundException("Tài nguyên không tồn tại");
        return mapper.map(file, FileResponse.class);
    }

    @Override
    public Page<FileResponse> getFiles(String author, Pageable pageable) {
        return fileRepo.findAllByCreatedBy(author, pageable)
                .map(item -> mapper.map(item, FileResponse.class));
    }
}
