package com.newswebsite.main.service;

import com.newswebsite.main.dto.response.FileResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IFileReader {
    FileResponse getFile(String alias);

    Page<FileResponse> getFiles(String author, Pageable pageable);
}
