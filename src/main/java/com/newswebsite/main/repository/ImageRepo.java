package com.newswebsite.main.repository;

import com.newswebsite.main.entity.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepo extends JpaRepository<Image, Long> {
    Image findByTitle(String title);

    Image findByAlias(String alias);

    Image findByUrl(String url);

    Page<Image> findAllByCreatedBy(String createdBy, Pageable pageable);
}
