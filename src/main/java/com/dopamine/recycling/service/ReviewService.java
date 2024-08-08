package com.dopamine.recycling.service;

import com.dopamine.recycling.domain.dto.ReviewRequestDto;
import com.dopamine.recycling.domain.entity.Review;
import com.dopamine.recycling.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public Review save(ReviewRequestDto request) {
        return reviewRepository.save(request.toEntity());
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public List<Review> getAllReviewsByProductId(Long productId) {
        return reviewRepository.findAllByProductId(productId);
    }

    @Transactional
    public void updateReview(Long reviewId, ReviewRequestDto request) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review == null) {
            return;
        }

        reviewRepository.updateReview(reviewId, request.getContent());
    }

    @Transactional
    public void deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review == null) {
            return;
        }

        reviewRepository.deleteReviewById(reviewId);
    }
}
