package com.newswebsite.main.service.articleservice;

import com.newswebsite.main.dto.ArticleDTO;
import com.newswebsite.main.dto.response.FeaturedArticleResponse;
import com.newswebsite.main.dto.response.LatestArticleResponse;
import com.newswebsite.main.dto.response.PopularArticleResponse;
import com.newswebsite.main.entity.Article;
import com.newswebsite.main.enums.ArticleState;
import com.newswebsite.main.exception.ArticleNotFoundException;
import com.newswebsite.main.mapper.ArticleMapper;
import com.newswebsite.main.mapper.CollectionMapper;
import com.newswebsite.main.repository.ArticleRepo;
import com.newswebsite.main.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ArticleReader implements IArticleReader {
    private final ArticleRepo articleRepo;

    private final CategoryRepo categoryRepo;

    private final MessageSource msg;

    private final CollectionMapper mapper;

    private final ArticleMapper articleMapper;

    @Autowired
    public ArticleReader(
            ArticleRepo articleRepo,
            CategoryRepo categoryRepo,
            MessageSource msg,
            CollectionMapper mapper,
            ArticleMapper articleMapper
    ) {
        this.articleRepo = articleRepo;
        this.categoryRepo = categoryRepo;
        this.msg = msg;
        this.mapper = mapper;
        this.articleMapper = articleMapper;
    }

    @Override
    public ArticleDTO getById(long id) {
        Article article = articleRepo.findOne(id);
        if (article == null) throw new ArticleNotFoundException(msg.getMessage("article.not.found", null, null) + id);
        return mapper.map(article, ArticleDTO.class);
    }

    @Override
    public ArticleDTO getByAlias(String alias) {
        Article article = articleRepo.findByAlias(alias);
        if (article == null)
            throw new ArticleNotFoundException(msg.getMessage("article.not.found", null, null) + alias);
        return mapper.map(article, ArticleDTO.class);
    }

    @Override
    public ArticleDTO getPublished(String alias) {
        Article article = articleRepo.findByAliasAndStateCode(alias, ArticleState.PUBLISHED.name());
        if (article == null)
            throw new ArticleNotFoundException(msg.getMessage("article.not.found", null, null) + alias);
        return mapper.map(article, ArticleDTO.class);
    }

    @Override
    public Page<ArticleDTO> search(String q, List<Long> categoryIds, Date startDate, Pageable pageable) {
        return articleRepo.search(q, categoryIds, startDate, pageable)
                .map(item -> mapper.map(item, ArticleDTO.class));
    }

    @Override
    public Page<LatestArticleResponse> getLatestArticles(Pageable pageable) {
        return articleRepo.findAllLatestArticles(ArticleState.PUBLISHED.name(), pageable)
                .map(item -> mapper.map(item, LatestArticleResponse.class));
    }

    @Override
    public Page<ArticleDTO> getAll(Pageable pageable) {
        return articleRepo.findAll(pageable)
                .map(item -> mapper.map(item, ArticleDTO.class));
    }

    @Override
    public Page<FeaturedArticleResponse> getFeaturedArticles(Pageable pageable) {
        return articleRepo.findAllPublishedAndFeaturedArticles(true, ArticleState.PUBLISHED.name(), pageable)
                .map(article -> mapper.map(article, FeaturedArticleResponse.class));
    }

    @Override
    public Page<ArticleDTO> getPendingArticles(Pageable pageable) {
        return articleRepo.findAllLatestArticles(ArticleState.PENDING.name(), pageable)
                .map(item -> mapper.map(item, ArticleDTO.class));
    }

    @Override
    public Page<PopularArticleResponse> getAllPublished(Pageable pageable) {
        return articleRepo.findAllByStateCode(ArticleState.PUBLISHED.name(), pageable)
                .map(a -> mapper.map(a, PopularArticleResponse.class));
    }

    @Override
    public Page<ArticleDTO> getTrashArticles(Pageable pageable) {
        return articleRepo.findAllLatestArticles(ArticleState.TRASH.name(), pageable)
                .map(item -> mapper.map(item, ArticleDTO.class));
    }

    @Override
    public Page<ArticleDTO> getNotTrashArticles(Pageable pageable) {
        return articleRepo.findAllByStateCodeNot(ArticleState.TRASH.name(), pageable)
                .map(item -> mapper.map(item, ArticleDTO.class));
    }

    @Override
    public Page<ArticleDTO> getAllByAuthorAndStateCode(String author, String stateCode, Pageable pageable) {
        Page<Article> contents = stateCode == null ?
                articleRepo.findAllByCreatedByAndStateCodeNot(author, ArticleState.TRASH.name(), pageable) :
                articleRepo.findAllByCreatedByAndStateCode(author, stateCode, pageable);
        return contents
                .map(item -> mapper.map(item, ArticleDTO.class));
    }

    @Override
    public Page<ArticleDTO> getAllByFeaturedAndAuthor(boolean featured, String author, Pageable pageable) {
        return articleRepo.findAllByFeaturedAndCreatedByAndStateCodeNot(featured, author, ArticleState.TRASH.name(), pageable)
                .map(item -> mapper.map(item, ArticleDTO.class));
    }

    @Override
    public Page<ArticleDTO> getAllPublishedByCategory(String categoryAlias, Pageable pageable) {
        return articleRepo.searchAllPublishedByCategoryAlias(categoryAlias, pageable)
                .map(item -> mapper.map(item, ArticleDTO.class));
    }
}
