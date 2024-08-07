package com.dopamine.recycling.domain.dto;

import com.dopamine.recycling.domain.entity.Review;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewResponseDto {
    private Long id;
    private Long userId;
    private Long productId;
    private String content;
    private boolean isDeleted;

    public ReviewResponseDto(Review review) {
        this.id = review.getId();
        this.userId = review.getUserId();
        this.productId = review.getProductId();
        this.content = review.getContent();
        this.isDeleted = review.isDeleted();
    }
}
