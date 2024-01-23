package com.newswebsite.main.repository;

import com.newswebsite.main.entity.File;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepo extends JpaRepository<File, Long> {
    File findByTitle(String title);

    File findByAlias(String alias);

    File findByUrl(String url);

    Page<File> findAllByCreatedBy(String createdBy, Pageable pageable);
}
