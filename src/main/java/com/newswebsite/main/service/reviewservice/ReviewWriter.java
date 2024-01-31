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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ReviewWriter implements IReviewWriter {

    private final ReviewRepo reviewRepo;

    private final ArticleRepo articleRepo;
    private final UserRepo userRepo;

    private final CollectionMapper mapper = new CollectionMapper();

    @Autowired
    public ReviewWriter(ReviewRepo reviewRepo, ArticleRepo articleRepo, UserRepo userRepo) {
        this.reviewRepo = reviewRepo;
        this.articleRepo = articleRepo;
        this.userRepo = userRepo;
    }

    @Override
    @Transactional
    public ReviewDTO save(ReviewDTO reviewDTO) {
        Review review = mapper.map(reviewDTO, Review.class);
        Article article = articleRepo.findOne(reviewDTO.getArticleId());
        if (article == null) throw new ArticleNotFoundException("Bài viết không tồn tại");
        User user = userRepo.findOne(reviewDTO.getUserId());
        if (user == null) throw new UsernameNotFoundException("Người dùng không tồn tại");
        if (reviewDTO.getParentId() != null) {
            Review parent = reviewRepo.findOne(reviewDTO.getParentId());
            if (parent == null) throw new ReviewNotFoundException("Bình luận không tồn tại");
            review.setParent(parent);
        }
        review.setUser(user);
        review.setArticle(article);
        review = reviewRepo.save(review);
        return mapper.map(review, ReviewDTO.class);
    }
}
