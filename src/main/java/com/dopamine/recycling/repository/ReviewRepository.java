package com.dopamine.recycling.repository;

import com.dopamine.recycling.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("select r from Review r where r.productId = :productId")
    List<Review> findAllByProductId(@Param("productId") long productId);

    @Modifying
    @Query("update Review r set r.content = :content where r.id = :id")
    void updateReview(@Param("id") long id, @Param("content") String content);

    @Modifying
    @Query("update Review  r set r.isDeleted = true where r.id = :id")
    void deleteReviewById(@Param("id") long id);
}
