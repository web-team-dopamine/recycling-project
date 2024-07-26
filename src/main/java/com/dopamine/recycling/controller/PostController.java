package com.dopamine.recycling.controller;

import com.dopamine.recycling.domain.dto.PostRequestDto;
import com.dopamine.recycling.domain.dto.PostResponseDto;
import com.dopamine.recycling.domain.entity.Post;
import com.dopamine.recycling.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponseDto> savePost(@RequestBody PostRequestDto request) {
        Post post = postService.save(request);
        PostResponseDto response = post.toResponse();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDto>> findAllPosts() {
        List<Post> postList = postService.getAllPosts();

        if(postList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<PostResponseDto> responseList = postList.stream()
                .map(PostResponseDto::new)
                .toList();
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> findPostById(@PathVariable("id") Long id) {
        Post post = postService.getPostById(id);

        if(post == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new PostResponseDto(post));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePost(@PathVariable("id") Long id, @RequestBody PostRequestDto request) {
        try {
            postService.updatePost(id, request);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") Long id) {
        postService.deletePostById(id);
        return ResponseEntity.ok().build();
    }

}
