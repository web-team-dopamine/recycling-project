package com.dopamine.recycling.domain.dto;

import com.dopamine.recycling.domain.entity.Recommend;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecommendRequestDto {
    private Long userId;
    private Long postId;

    public Recommend toEntity() {
        return Recommend.builder()
                .userId(userId)
                .postId(postId)
                .build();
    }
}
