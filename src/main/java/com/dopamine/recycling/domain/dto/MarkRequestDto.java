package com.dopamine.recycling.domain.dto;

import com.dopamine.recycling.domain.entity.Mark;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MarkRequestDto {
    private Long userId;
    private Long productId;

    public Mark toEntity() {
        return Mark.builder()
                .userId(userId)
                .productId(productId)
                .build();
    }
}
