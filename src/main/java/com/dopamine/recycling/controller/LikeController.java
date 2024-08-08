package com.dopamine.recycling.controller;

import com.dopamine.recycling.domain.dto.LikeRequestDto;
import com.dopamine.recycling.service.LikeService;
import com.dopamine.recycling.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;
    private final ReviewService reviewService;

    @GetMapping("/add")
    public ResponseEntity<Void> addLike(@RequestBody LikeRequestDto request) {
        Long userId = 1L; // 임의 지정
        boolean successAddLike = likeService.addLike(request.getReviewId(), userId);

        return successAddLike ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @GetMapping("/remove")
    public ResponseEntity<Void> removeLike(@RequestBody LikeRequestDto request) {
        Long userId = 1L; // 임의 지정
        boolean successRemoveLike = likeService.removeLike(request.getReviewId(), userId);

        return successRemoveLike ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }
}
