package com.newswebsite.main.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FileResponse extends ContentResponse {
    private long size;
    private String url;
    private String directory;
}
