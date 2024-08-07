package com.dopamine.recycling.domain.dto;

import com.dopamine.recycling.domain.entity.Review;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewRequestDto {
    private Long userId;
    private Long productId;
    private String content;
    private boolean isDeleted;

    public Review toEntity() {
        return Review.builder()
                .userId(userId)
                .productId(productId)
                .content(content)
                .isDeleted(false)
                .build();
    }
}
