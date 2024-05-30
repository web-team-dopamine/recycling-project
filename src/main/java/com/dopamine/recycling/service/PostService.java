package com.dopamine.recycling.service;

import com.dopamine.recycling.domain.dto.PostRequestDto;
import com.dopamine.recycling.domain.entity.Post;
import com.dopamine.recycling.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public List<Post> getPostByPostTypeProof(String proof) {
        return postRepository.findByPostType(proof);
    }

    public List<Post> getPostByPostTypePromotion(String promotion) {
        return postRepository.findByPostType(promotion);
    }

    public List<Post> getPostByPostTypeComplain(String complain) {
        return postRepository.findByPostType(complain);
    }

    @Transactional
    public void updatePost(Long id, PostRequestDto request) {
        Post post = postRepository.findById(id).orElse(null);

        if(post == null) {
            return;
        }

        postRepository.updatePostById(id, request.getPostType(), request.getTitle(), request.getContent());
    }

    public void deletePostById(Long id) {
        Post post = postRepository.findById(id).orElse(null);

        if(post == null) {
            return;
        }

        postRepository.deleteById(id);
    }
}
