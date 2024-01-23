package com.newswebsite.main.service.fileservice;

import com.newswebsite.main.dto.request.FileRequest;
import com.newswebsite.main.dto.response.FileResponse;

import java.io.IOException;

public interface IFileWriter {
    FileResponse handleUpload(FileRequest fileRequest) throws IOException;

    FileResponse save(FileResponse fileResponse);

    void delete(long id);
}
