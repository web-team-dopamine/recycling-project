package com.dopamine.recycling.repository;

import com.dopamine.recycling.domain.entity.Recommend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecommendRepository extends JpaRepository<Recommend, Long> {
    Optional<Recommend> findByPostIdAndUserId(Long postId, Long userId);
}
