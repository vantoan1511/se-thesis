package com.newswebsite.main.service.reviewservice;

import com.newswebsite.main.dto.ReviewDTO;
import com.newswebsite.main.entity.Article;
import com.newswebsite.main.entity.Review;
import com.newswebsite.main.entity.User;
import com.newswebsite.main.exception.ArticleNotFoundException;
import com.newswebsite.main.exception.ReviewNotFoundException;
import com.newswebsite.main.mapper.CollectionMapper;
import com.newswebsite.main.repository.ArticleRepo;
import com.newswebsite.main.repository.ReviewRepo;
import com.newswebsite.main.repository.UserRepo;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ReviewWriter implements IReviewWriter {

    private final ReviewRepo reviewRepo;

    private final ArticleRepo articleRepo;
    private final UserRepo userRepo;

    private final CollectionMapper mapper;

    @Autowired
    public ReviewWriter(ReviewRepo reviewRepo, ArticleRepo articleRepo, UserRepo userRepo, CollectionMapper mapper) {
        this.reviewRepo = reviewRepo;
        this.articleRepo = articleRepo;
        this.userRepo = userRepo;
        this.mapper = mapper;
    }

    @Override
    public ReviewDTO save(ReviewDTO reviewDTO) {
        Review review = new Review();
        Article article = articleRepo.findOne(reviewDTO.getArticleId());
        if (article == null) throw new ArticleNotFoundException("Bài viết không tồn tại");
        User user = userRepo.findOne(reviewDTO.getUserId());
        if (user == null) throw new UsernameNotFoundException("Người dùng không tồn tại");
        if (reviewDTO.getParentId() != null) {
            Review parent = reviewRepo.findOne(reviewDTO.getParentId());
            if (parent == null) throw new ReviewNotFoundException("Bình luận không tồn tại");
            review.setParent(parent);
        }

        review.setText(StringEscapeUtils.escapeHtml4(reviewDTO.getText()));
        review.setUser(user);
        review.setArticle(article);

        review = reviewRepo.save(review);
        return mapper.map(review, ReviewDTO.class);
    }

    @Override
    public ReviewDTO update(ReviewDTO reviewDTO) {
        Review review = reviewRepo.findOne(reviewDTO.getId());

        if (review == null) throw new ReviewNotFoundException("Bình luận không còn tồn tại");

        review.setText(StringEscapeUtils.escapeHtml4(reviewDTO.getText()));

        return mapper.map(reviewRepo.save(review), ReviewDTO.class);
    }

    @Override
    public void delete(long id) {
        if (!reviewRepo.exists(id)) throw new ReviewNotFoundException("Bình luận không còn tồn tại");
        reviewRepo.delete(id);
    }

    @Override
    public void deleteMultiple(List<Long> ids) {
        for (long id : ids) {
            delete(id);
        }
    }
}
