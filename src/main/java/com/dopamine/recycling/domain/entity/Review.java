package com.dopamine.recycling.domain.entity;

import com.dopamine.recycling.domain.dto.ReviewResponseDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long productId;
    private String content;
    private boolean isDeleted;

    public ReviewResponseDto toResponse() {
        return ReviewResponseDto.builder()
                .id(id)
                .userId(userId)
                .productId(productId)
                .content(content)
                .isDeleted(isDeleted)
                .build();
    }
}
