package com.dopamine.recycling.repository;

import com.dopamine.recycling.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("select r from Review r where r.productId = :productId")
    List<Review> findAllByProductId(@Param("productId") long productId);
}
