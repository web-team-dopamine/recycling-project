package com.dopamine.recycling.domain.dto;

import com.dopamine.recycling.domain.entity.Like;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikeRequestDto {
    private Long userId;
    private Long reviewId;

    public Like toEntity() {
        return Like.builder()
                .userId(userId)
                .reviewId(reviewId)
                .build();
    }
}
