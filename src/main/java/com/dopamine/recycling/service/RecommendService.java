package com.dopamine.recycling.service;

import com.dopamine.recycling.domain.dto.RecommendRequestDto;
import com.dopamine.recycling.domain.entity.Recommend;
import com.dopamine.recycling.repository.RecommendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecommendService {
    private final RecommendRepository recommendRepository;

    @Transactional
    public boolean addRecommend(Long postId, Long userId) {
        Optional<Recommend> existRecommend = recommendRepository.findByPostIdAndUserId(postId, userId);

        if (existRecommend.isPresent()) {
            return false;
        }

        RecommendRequestDto request  = new RecommendRequestDto();
        request.setPostId(postId);
        request.setUserId(userId);

        recommendRepository.save(request.toEntity());
        return true;
    }

    @Transactional
    public boolean removeRecommend(Long postId, Long userId) {
        Optional<Recommend> existRecommend = recommendRepository.findByPostIdAndUserId(postId, userId);

        if(existRecommend.isPresent()) {
            recommendRepository.delete(existRecommend.get());
            return true;
        } else {
            return false;
        }
    }
}