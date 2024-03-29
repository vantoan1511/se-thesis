package com.newswebsite.main.service.articleservice;

import com.newswebsite.main.dto.ArticleDTO;
import com.newswebsite.main.dto.request.ArticleCreationRequest;
import com.newswebsite.main.entity.Article;
import com.newswebsite.main.entity.Category;
import com.newswebsite.main.entity.State;
import com.newswebsite.main.entity.User;
import com.newswebsite.main.enums.ArticleState;
import com.newswebsite.main.exception.ArticleNotFoundException;
import com.newswebsite.main.exception.CategoryNotFoundException;
import com.newswebsite.main.exception.InvalidArticleOperationException;
import com.newswebsite.main.exception.StateCodeNotFoundException;
import com.newswebsite.main.repository.ArticleRepo;
import com.newswebsite.main.repository.CategoryRepo;
import com.newswebsite.main.repository.StateRepo;
import com.newswebsite.main.security.SecurityUtil;
import com.newswebsite.main.service.stateservice.*;
import com.newswebsite.main.utils.SlugGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ArticleWriter implements IArticleWriter {

    private final ArticleRepo articleRepo;
    private final CategoryRepo categoryRepo;
    private final StateRepo stateRepo;
    private final MessageSource msg;
    private final ModelMapper mapper;

    private IStateService stateService;

    @Autowired
    public ArticleWriter(
            ArticleRepo articleRepo,
            CategoryRepo categoryRepo,
            MessageSource msg,
            StateRepo stateRepo,
            ModelMapper mapper
    ) {
        this.articleRepo = articleRepo;
        this.categoryRepo = categoryRepo;
        this.msg = msg;
        this.stateRepo = stateRepo;
        this.mapper = mapper;
    }

    @Override
    public void increaseTraffic(String alias) {
        Article article = articleRepo.findByAlias(alias);
        if (article == null)
            throw new ArticleNotFoundException("Không tìm thấy bài viết có alias [%s] ".formatted(alias));
        long currentTraffic = article.getTraffic();
        currentTraffic += 1;
        article.setTraffic(currentTraffic);
        articleRepo.save(article);
    }

    @Override
    public void submit(long id) {
        Article article = getArticleFromId(id);
        stateService = createStateService(article.getState().getCode());
        stateService.submit(article);
        articleRepo.save(article);
    }

    @Override
    public void trash(long id) {
        Article article = getArticleFromId(id);
        stateService = createStateService(article.getState().getCode());
        stateService.toTrash(article);
        articleRepo.save(article);
    }

    @Override
    public void approve(long id) {
        Article article = getArticleFromId(id);
        stateService = createStateService(article.getState().getCode());
        stateService.approve(article);
        articleRepo.save(article);
    }

    @Override
    public void reject(long id) {
        Article article = getArticleFromId(id);
        stateService = createStateService(article.getState().getCode());
        stateService.reject(article);
        articleRepo.save(article);
    }

    @Override
    public void publish(long id) {
        Article article = getArticleFromId(id);
        stateService = createStateService(article.getState().getCode());
        stateService.publish(article);
        article.setPublishedAt(Date.from(Instant.now()));
        articleRepo.save(article);
    }

    @Override
    public void edit(long id) {
        Article article = getArticleFromId(id);
        stateService = createStateService(article.getState().getCode());
        stateService.edit(article);
        articleRepo.save(article);
    }

    @Override
    public void unPublish(long id) {
        Article article = getArticleFromId(id);
        stateService = createStateService(article.getState().getCode());
        stateService.unPublish(article);
        articleRepo.save(article);
    }

    @Override
    public void restore(long id) {
        Article article = getArticleFromId(id);
        stateService = createStateService(article.getState().getCode());
        stateService.restore(article);
        articleRepo.save(article);
    }

    @Override
    public void approveMultiple(List<Long> ids) {
        for (long id : ids) {
            approve(id);
        }
    }

    @Override
    public void rejectMultiple(List<Long> ids) {
        for (long id : ids) {
            reject(id);
        }
    }

    @Override
    public void trashMultiple(List<Long> ids) {
        for (long id : ids) {
            trash(id);
        }
    }

    @Override
    public void restoreMultiple(List<Long> ids) {
        for (long id : ids) {
            restore(id);
        }
    }

    @Override
    public void setFeatured(long id, boolean featured) {
        Article article = articleRepo.findOne(id);
        if (article == null) throw new ArticleNotFoundException("Không tìm thấy bài viết với id [%s]".formatted(id));
        article.setFeatured(featured);
        articleRepo.save(article);
    }

    @Override
    public void setFeaturedMultiple(List<Long> ids, boolean featured) {
        for (long id : ids) {
            setFeatured(id, featured);
        }
    }

    @Override
    public ArticleDTO save(ArticleCreationRequest articleCreationRequest) {
        Category category = categoryRepo.findByAlias(articleCreationRequest.getCategoryAlias());
        if (category == null)
            throw new CategoryNotFoundException("Không tìm thấy chuyên mục với alias [%s]".formatted(articleCreationRequest.getCategoryAlias()));
        Article article = mapper.map(articleCreationRequest, Article.class);
        article.setCategory(category);

        State state = stateRepo.findByCode(ArticleState.DRAFT.name());
        article.setState(state);

        Article oldArticle = new Article();
        if (articleCreationRequest.getId() != null) {
            oldArticle = articleRepo.findOne(articleCreationRequest.getId());
            if (!oldArticle.getState().getCode().equals(ArticleState.DRAFT.name()))
                throw new InvalidArticleOperationException("Bài viết có id [%s] hiện không thể chỉnh sửa".formatted(oldArticle.getId()));
            article.setCreatedAt(oldArticle.getCreatedAt());
            article.setCreatedBy(oldArticle.getCreatedBy());
            article.setPublishedAt(oldArticle.getPublishedAt());
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

        Long authorId = SecurityUtil.getUser().getId();
        User author = new User();
        author.setId(authorId);
        article.setAuthor(author);

        article = articleRepo.save(article);

        return mapper.map(article, ArticleDTO.class);
    }

    @Override
    public void delete(Long id) {
        Article article = articleRepo.findOne(id);
        if (article == null) throw new ArticleNotFoundException(msg.getMessage("article.not.found", null, null));
        if (!article.getState().getCode().equals(ArticleState.TRASH.name()))
            throw new InvalidArticleOperationException(msg.getMessage("article.operation.invalid", null, null));
        articleRepo.delete(id);
    }

    @Override
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
        if (article == null) throw new ArticleNotFoundException("Không tìm thấy bài viết có id [%s]".formatted(id));
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
