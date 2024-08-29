package com.dopamine.recycling.repository;

import com.dopamine.recycling.domain.entity.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface MarkRepository extends JpaRepository<Mark, Long> {
    Optional<Mark> findByProductIdAndUserId(Long productId, Long userId);
}
