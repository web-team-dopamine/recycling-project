package com.dopamine.recycling.domain.entity;

import com.dopamine.recycling.domain.dto.PostRequestDto;
import com.dopamine.recycling.domain.dto.PostResponseDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String postType;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long views;
    private Long likes;
    private boolean isDeleted;

    public PostResponseDto toResponse() {
        return PostResponseDto.builder()
                .id(id)
                .postType(postType)
                .title(title)
                .content(content)
                .views(views)
                .likes(likes)
                .isDeleted(isDeleted)
                .build();
    }
}
