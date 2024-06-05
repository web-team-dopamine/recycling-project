package com.dopamine.recycling.domain.dto;

import com.dopamine.recycling.domain.entity.Comments;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CommentsRequestDto {
    private Long id;
    private Long userId;
    private Long postId;
    private String content;
    private boolean isDeleted;

    public Comments toEntity(Long postId, Long userId) {
        return Comments.builder()
                .userId(userId)
                .postId(postId)
                .content(content)
                .isDeleted(isDeleted)
                .build();
    }
}
