package com.dopamine.recycling.controller;

import com.dopamine.recycling.domain.dto.RecommendRequestDto;
import com.dopamine.recycling.service.PostService;
import com.dopamine.recycling.service.RecommendService;
import com.dopamine.recycling.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recommend")
@RequiredArgsConstructor
public class RecommendController {
    private final RecommendService recommendService;
    private final PostService postService;

    @PostMapping("/add")
    public ResponseEntity<Void> addRecommend(@RequestBody RecommendRequestDto request) {
        Long userId = 1L;
        boolean successAddRecommend = recommendService.addRecommend(request.getPostId(), userId);

        return successAddRecommend ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/remove")
    public ResponseEntity<Void> removeRecommend(@RequestBody RecommendRequestDto request) {
        Long userId = 1L;
        boolean successRemoveRecommend = recommendService.removeRecommend(request.getPostId(), userId);

        return successRemoveRecommend ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
