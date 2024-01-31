package com.newswebsite.main.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class Content extends Base {

    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "alias", unique = true, nullable = false)
    private String alias;
    @Column(name = "description")
    private String description;
    @Column(name = "published_at")
    private Date publishedAt;
}
