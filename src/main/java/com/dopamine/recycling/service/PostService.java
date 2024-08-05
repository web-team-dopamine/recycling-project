package com.dopamine.recycling.service;

import com.dopamine.recycling.domain.dto.PostRequestDto;
import com.dopamine.recycling.domain.entity.Post;
import com.dopamine.recycling.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Post save(PostRequestDto post) {
        return postRepository.save(post.toEntity());
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    @Transactional
    public void updatePost(Long id, PostRequestDto request) {
        Post post = postRepository.findById(id).orElse(null);

        if(post == null) {
            return;
        }

        postRepository.updatePostById(id, request.getTitle(), request.getContent(), LocalDateTime.now());
    }

    public void deletePostById(Long id) {
        Post post = postRepository.findById(id).orElse(null);

        if(post == null) {
            return;
        }

        postRepository.deleteById(id);
    }
}
