package com.dopamine.recycling.domain.dto;

import com.dopamine.recycling.domain.entity.Post;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRequestDto {
    private String postType;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long views;
    private Long likes;

    public Post toEntity() {
        return Post.builder()
                .postType(postType)
                .title(title)
                .content(content)
                .createdAt(LocalDateTime.now())
                .updatedAt(null)
                .views(0L)
                .likes(0L)
                .isDeleted(false)
                .build();
    }
}
