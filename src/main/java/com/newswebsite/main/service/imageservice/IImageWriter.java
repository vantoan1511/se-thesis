package com.newswebsite.main.service.imageservice;

import com.newswebsite.main.dto.request.ImageRequest;
import com.newswebsite.main.dto.response.ImageResponse;

import java.io.IOException;
import java.util.List;

public interface IImageWriter {
    ImageResponse handleUpload(ImageRequest fileRequest) throws IOException;

    ImageResponse save(ImageResponse fileResponse);

    void delete(long id);
    void deleteMultiple(List<Long> ids);
}
