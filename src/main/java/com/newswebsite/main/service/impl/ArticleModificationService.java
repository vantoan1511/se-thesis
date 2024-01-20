package com.newswebsite.main.service.impl;

import com.newswebsite.main.dto.ArticleDTO;
import com.newswebsite.main.entity.Article;
import com.newswebsite.main.entity.Category;
import com.newswebsite.main.entity.State;
import com.newswebsite.main.enums.ArticleState;
import com.newswebsite.main.exception.ArticleNotFoundException;
import com.newswebsite.main.exception.CategoryCodeNotFoundException;
import com.newswebsite.main.exception.InvalidArticleOperationException;
import com.newswebsite.main.repository.ArticleRepo;
import com.newswebsite.main.repository.CategoryRepo;
import com.newswebsite.main.repository.StateRepo;
import com.newswebsite.main.service.IArticleModificationService;
import com.newswebsite.main.utils.SlugGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    @Transactional
    public void submit(long id) {
        Article article = articleRepo.findOne(id);
        if (article == null) throw new ArticleNotFoundException(msg.getMessage("article.not.found", null, null));
        if (!article.getState().getCode().equals(ArticleState.DRAFT.name()))
            throw new InvalidArticleOperationException(msg.getMessage("article.operation.invalid", null, null));
        State pendingState = stateRepo.findByCode(ArticleState.PENDING.name());
        article.setState(pendingState);
        articleRepo.save(article);
    }

    @Override
    @Transactional
    public void trash(long id) {
        Article article = articleRepo.findOne(id);
        if (article == null) throw new ArticleNotFoundException(msg.getMessage("article.not.found", null, null));
        if (!article.getState().getCode().equals(ArticleState.DRAFT.name()))
            throw new InvalidArticleOperationException(msg.getMessage("article.operation.invalid", null, null));
        State trashState = stateRepo.findByCode(ArticleState.TRASH.name());
        article.setState(trashState);
        articleRepo.save(article);
    }

    @Override
    @Transactional
    public void approve(long id) {
        Article article = articleRepo.findOne(id);
        if (article == null) throw new ArticleNotFoundException(msg.getMessage("article.not.found", null, null));
        if (!article.getState().getCode().equals(ArticleState.PENDING.name()))
            throw new InvalidArticleOperationException(msg.getMessage("article.operation.invalid", null, null));
        State approvedState = stateRepo.findByCode(ArticleState.APPROVED.name());
        article.setState(approvedState);
        articleRepo.save(article);
    }

    @Override
    @Transactional
    public void reject(long id) {
        Article article = articleRepo.findOne(id);
        if (article == null) throw new ArticleNotFoundException(msg.getMessage("article.not.found", null, null));
        if (!article.getState().getCode().equals(ArticleState.PENDING.name()))
            throw new InvalidArticleOperationException(msg.getMessage("article.operation.invalid", null, null));
        State draftState = stateRepo.findByCode(ArticleState.DRAFT.name());
        article.setState(draftState);
        articleRepo.save(article);
    }

    @Override
    @Transactional
    public void publish(long id) {
        Article article = articleRepo.findOne(id);
        if (article == null) throw new ArticleNotFoundException(msg.getMessage("article.not.found", null, null));
        if (!article.getState().getCode().equals(ArticleState.APPROVED.name()) &&
                !article.getState().getCode().equals(ArticleState.UNPUBLISHED.name()))
            throw new InvalidArticleOperationException(msg.getMessage("article.operation.invalid", null, null));
        State publishedState = stateRepo.findByCode(ArticleState.PUBLISHED.name());
        article.setState(publishedState);
        articleRepo.save(article);
    }

    @Override
    @Transactional
    public void edit(long id) {
        Article article = articleRepo.findOne(id);
        if (article == null) throw new ArticleNotFoundException(msg.getMessage("article.not.found", null, null));
        if (!article.getState().getCode().equals(ArticleState.APPROVED.name()) &&
                !article.getState().getCode().equals(ArticleState.UNPUBLISHED.name()))
            throw new InvalidArticleOperationException(msg.getMessage("article.operation.invalid", null, null));
        State draftState = stateRepo.findByCode(ArticleState.DRAFT.name());
        article.setState(draftState);
        articleRepo.save(article);
    }

    @Override
    @Transactional
    public void unPublish(long id) {
        Article article = articleRepo.findOne(id);
        if (article == null) throw new ArticleNotFoundException(msg.getMessage("article.not.found", null, null));
        if (!article.getState().getCode().equals(ArticleState.PUBLISHED.name()))
            throw new InvalidArticleOperationException(msg.getMessage("article.operation.invalid", null, null));
        State unPublishedState = stateRepo.findByCode(ArticleState.UNPUBLISHED.name());
        article.setState(unPublishedState);
        articleRepo.save(article);
    }

    @Override
    @Transactional
    public void restore(long id) {
        Article article = articleRepo.findOne(id);
        if (article == null) throw new ArticleNotFoundException(msg.getMessage("article.not.found", null, null));
        if (!article.getState().getCode().equals(ArticleState.TRASH.name()))
            throw new InvalidArticleOperationException(msg.getMessage("article.operation.invalid", null, null));
        State draftState = stateRepo.findByCode(ArticleState.DRAFT.name());
        article.setState(draftState);
        articleRepo.save(article);
    }

    @Override
    @Transactional
    public void approveMultiple(List<Long> ids) {
        for (long id : ids) {
            approve(id);
        }
    }

    @Override
    @Transactional
    public void rejectMultiple(List<Long> ids) {
        for (long id : ids) {
            reject(id);
        }
    }

    @Override
    @Transactional
    public void trashMultiple(List<Long> ids) {
        for (long id : ids) {
            trash(id);
        }
    }

    @Override
    @Transactional
    public void restoreMultiple(List<Long> ids) {
        for (long id : ids) {
            restore(id);
        }
    }

    @Override
    @Transactional
    public ArticleDTO save(ArticleDTO articleDTO) {
        Category category = categoryRepo.findByCategoryCode(articleDTO.getCategoryCode());
        if (category == null)
            throw new CategoryCodeNotFoundException(msg.getMessage("category.not.found", null, null) + articleDTO.getCategoryCode());

        Article article = mapper.map(articleDTO, Article.class);
        article.setCategory(category);

        State state = stateRepo.findByCode(ArticleState.DRAFT.name());
        article.setState(state);

        Article oldArticle = new Article();
        if (articleDTO.getId() != null) {
            oldArticle = articleRepo.findOne(articleDTO.getId());
            if (!oldArticle.getState().getCode().equals(ArticleState.DRAFT.name()))
                throw new InvalidArticleOperationException("Bài viết hiện không thể chỉnh sửa");
            article.setCreatedAt(oldArticle.getCreatedAt());
            article.setCreatedBy(oldArticle.getCreatedBy());
            article.setTraffic(oldArticle.getTraffic());
            article.setState(oldArticle.getState());
        }
        String newAlias = SlugGenerator.slugify.slugify(articleDTO.getAlias());
        String oldAlias = oldArticle.getAlias();

        if (!newAlias.equals(oldAlias)) {
            if (newAlias.isBlank()) {
                newAlias = SlugGenerator.generateUniqueSlug(articleDTO.getTitle());
            }
            if (!isUniqueAlias(newAlias)) {
                newAlias = SlugGenerator.generateUniqueSlug(newAlias);
            }
        }
        article.setAlias(newAlias);
        return mapper.map(articleRepo.save(article), ArticleDTO.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Article article = articleRepo.findOne(id);
        if (article == null) throw new ArticleNotFoundException(msg.getMessage("article.not.found", null, null));
        if (!article.getState().getCode().equals(ArticleState.TRASH.name()))
            throw new InvalidArticleOperationException(msg.getMessage("article.operation.invalid", null, null));
        articleRepo.delete(id);
    }

    @Override
    @Transactional
    public void deleteArticles(List<Long> ids) {
        for (long id : ids) {
            delete(id);
        }
    }

    private boolean isUniqueAlias(String alias) {
        return articleRepo.findByAlias(alias) == null;
    }
}
