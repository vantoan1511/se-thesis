package com.newswebsite.main.service.articleservice;

import com.newswebsite.main.dto.ArticleDTO;
import com.newswebsite.main.entity.Article;
import com.newswebsite.main.entity.Category;
import com.newswebsite.main.entity.State;
import com.newswebsite.main.enums.ArticleState;
import com.newswebsite.main.exception.ArticleNotFoundException;
import com.newswebsite.main.exception.CategoryCodeNotFoundException;
import com.newswebsite.main.exception.InvalidArticleOperationException;
import com.newswebsite.main.exception.StateCodeNotFoundException;
import com.newswebsite.main.repository.ArticleRepo;
import com.newswebsite.main.repository.CategoryRepo;
import com.newswebsite.main.repository.StateRepo;
import com.newswebsite.main.service.stateservice.*;
import com.newswebsite.main.utils.SlugGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ArticleWriter implements IArticleWriter {

    private final ArticleRepo articleRepo;
    private final CategoryRepo categoryRepo;
    private final StateRepo stateRepo;
    private final MessageSource msg;
    private final ModelMapper mapper = new ModelMapper();

    private IStateService stateService;

    @Autowired
    public ArticleWriter(ArticleRepo articleRepo,
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
        Article article = getArticleFromId(id);
        stateService = createStateService(article.getState().getCode());
        stateService.submit(article);
        articleRepo.save(article);
    }

    @Override
    @Transactional
    public void trash(long id) {
        Article article = getArticleFromId(id);
        stateService = createStateService(article.getState().getCode());
        stateService.toTrash(article);
        articleRepo.save(article);
    }

    @Override
    @Transactional
    public void approve(long id) {
        Article article = getArticleFromId(id);
        stateService = createStateService(article.getState().getCode());
        stateService.approve(article);
        articleRepo.save(article);
    }

    @Override
    @Transactional
    public void reject(long id) {
        Article article = getArticleFromId(id);
        stateService = createStateService(article.getState().getCode());
        stateService.reject(article);
        articleRepo.save(article);
    }

    @Override
    @Transactional
    public void publish(long id) {
        Article article = getArticleFromId(id);
        stateService = createStateService(article.getState().getCode());
        stateService.publish(article);
        articleRepo.save(article);
    }

    @Override
    @Transactional
    public void edit(long id) {
        Article article = getArticleFromId(id);
        stateService = createStateService(article.getState().getCode());
        stateService.edit(article);
        articleRepo.save(article);
    }

    @Override
    @Transactional
    public void unPublish(long id) {
        Article article = getArticleFromId(id);
        stateService = createStateService(article.getState().getCode());
        stateService.unPublish(article);
        articleRepo.save(article);
    }

    @Override
    @Transactional
    public void restore(long id) {
        Article article = getArticleFromId(id);
        stateService = createStateService(article.getState().getCode());
        stateService.restore(article);
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
        Category category = categoryRepo.findByAlias(articleDTO.getCategoryAlias());
        if (category == null)
            throw new CategoryCodeNotFoundException(msg.getMessage("category.not.found", null, null) + articleDTO.getCategoryAlias());

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

        String newAlias = StringUtils.hasText(article.getAlias()) ? article.getAlias() : article.getTitle();
        newAlias = SlugGenerator.slugify.slugify(newAlias);
        String oldAlias = oldArticle.getAlias();
        if (!newAlias.equals(oldAlias) && !isUniqueAlias(newAlias)) {
            newAlias = SlugGenerator.generateUniqueSlug(newAlias);
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

    private Article getArticleFromId(long id) {
        Article article = articleRepo.findOne(id);
        if (article == null) throw new ArticleNotFoundException(msg.getMessage("article.not.found", null, null));
        return article;
    }

    private IStateService createStateService(String currentState) {
        switch (currentState) {
            case "DRAFT" -> {
                return new DraftState(stateRepo, currentState);
            }
            case "PENDING" -> {
                return new PendingState(stateRepo, currentState);
            }
            case "APPROVED" -> {
                return new ApprovedState(stateRepo, currentState);
            }
            case "PUBLISHED" -> {
                return new PublishedState(stateRepo, currentState);
            }
            case "UNPUBLISHED" -> {
                return new UnPublishedState(stateRepo, currentState);
            }
            case "TRASH" -> {
                return new TrashState(stateRepo, currentState);
            }
            default -> {
                throw new StateCodeNotFoundException("Unknown state");
            }
        }
    }
}
