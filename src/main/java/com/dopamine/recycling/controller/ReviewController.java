package com.dopamine.recycling.controller;

import com.dopamine.recycling.domain.dto.ReviewRequestDto;
import com.dopamine.recycling.domain.dto.ReviewResponseDto;
import com.dopamine.recycling.domain.entity.Review;
import com.dopamine.recycling.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewResponseDto> createReview(@RequestBody ReviewRequestDto request) {
        Review review = reviewService.save(request);
        ReviewResponseDto response = review.toResponse();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ReviewResponseDto>> getAllReviews() {
        List<Review> reviewList = reviewService.getAllReviews();

        if (reviewList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<ReviewResponseDto> responseList = reviewList.stream()
                .map(ReviewResponseDto::new)
                .toList();

        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ReviewResponseDto>> getReviewsForProduct(@PathVariable("productId") Long productId) {
        List<Review> reviewList = reviewService.getAllReviewsByProductId(productId);

        if (reviewList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<ReviewResponseDto> responseList = reviewList.stream()
                .map(ReviewResponseDto::new)
                .toList();

        return ResponseEntity.ok(responseList);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable("reviewId") Long reviewId, @RequestBody ReviewRequestDto request) {
        try {
            reviewService.updateReview(reviewId, request);
            return ResponseEntity.ok().build();
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable("reviewId") Long reviewId) {
        try{
            reviewService.deleteReview(reviewId);
            return ResponseEntity.ok().build();
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}