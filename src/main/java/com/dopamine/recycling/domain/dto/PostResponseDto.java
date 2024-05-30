package com.dopamine.recycling.domain.dto;

import com.dopamine.recycling.domain.entity.Post;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponseDto {

    private Long id;
    private String postType;
    private String title;
    private String content;
    private Long views;
    private Long likes;
    private boolean isDeleted;


    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.postType = post.getPostType();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.views = post.getViews();
        this.likes = post.getLikes();
        this.isDeleted = post.isDeleted();
    }
}
