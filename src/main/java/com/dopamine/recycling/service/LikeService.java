package com.dopamine.recycling.service;

import com.dopamine.recycling.domain.dto.LikeRequestDto;
import com.dopamine.recycling.domain.entity.Like;
import com.dopamine.recycling.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;

    @Transactional
    public boolean addLike(Long reviewId, Long userId) {
        Optional<Like> existLike = likeRepository.findByReviewIdAndUserId(reviewId, userId);

        if (existLike.isPresent()) {
            return false;
        }

        LikeRequestDto request = new LikeRequestDto();
        request.setReviewId(reviewId);
        request.setUserId(userId);

        likeRepository.save(request.toEntity());
        return true;
    }

    @Transactional
    public boolean removeLike(Long reviewId, Long userId) {
        Optional<Like> existLike = likeRepository.findByReviewIdAndUserId(reviewId, userId);

        if (existLike.isPresent()) {
            likeRepository.delete(existLike.get());
            return true;
        } else {
            return false;
        }
    }
}
