package com.newswebsite.main.service.impl;

import com.newswebsite.main.dto.ArticleDTO;
import com.newswebsite.main.entity.Article;
import com.newswebsite.main.entity.Category;
import com.newswebsite.main.entity.State;
import com.newswebsite.main.enums.ArticleState;
import com.newswebsite.main.exception.CategoryCodeNotFoundException;
import com.newswebsite.main.repository.ArticleRepo;
import com.newswebsite.main.repository.CategoryRepo;
import com.newswebsite.main.repository.StateRepo;
import com.newswebsite.main.service.IArticleModificationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ArticleModificationService implements IArticleModificationService {

    private final ArticleRepo articleRepo;
    private final CategoryRepo categoryRepo;

    private final StateRepo stateRepo;
    private final MessageSource msg;
    private final ModelMapper mapper = new ModelMapper();

    public ArticleModificationService(ArticleRepo articleRepo,
                                      CategoryRepo categoryRepo,
                                      MessageSource msg,
                                      StateRepo stateRepo) {
        this.articleRepo = articleRepo;
        this.categoryRepo = categoryRepo;
        this.msg = msg;
        this.stateRepo = stateRepo;
    }

    @Override
    public ArticleDTO save(ArticleDTO articleDTO) {
        Category category = categoryRepo.findByCategoryCode(articleDTO.getCategoryCode());
        if (category == null)
            throw new CategoryCodeNotFoundException(msg.getMessage("category.not.found", null, null) + articleDTO.getCategoryCode());
        Article article = mapper.map(articleDTO, Article.class);
        article.setCategory(category);
        State state = stateRepo.findByStateCode(ArticleState.DRAFT.name());
        if (articleDTO.getId() != null) {
            Article oldArticle = articleRepo.findOne(articleDTO.getId());
            article.setCreatedAt(oldArticle.getCreatedAt());
            article.setCreatedBy(oldArticle.getCreatedBy());
            article.setState(oldArticle.getState());
            article.setTraffic(oldArticle.getTraffic());
        }
        article.setState(state);
        return mapper.map(articleRepo.save(article), ArticleDTO.class);
    }

    @Override
    public void delete(Long id) {
        articleRepo.delete(id);
    }

    @Override
    public void deleteArticles(List<Long> ids) {
        for (long id : ids) {
            delete(id);
        }
    }
}
