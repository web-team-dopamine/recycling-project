package com.dopamine.recycling.repository;

import com.dopamine.recycling.domain.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByReviewIdAndUserId(Long reviewId, Long userId);
}
