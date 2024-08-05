package com.dopamine.recycling.repository;

import com.dopamine.recycling.domain.entity.Post;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    @Modifying
    @Transactional
    @Query("update Post p set p.title = :title, p.content = :content, p.updatedAt =  :now where p.id = :id ")
    void updatePostById(@Param("id") Long id, @Param("title") String title, @Param("content") String content, @Param("now") LocalDateTime now);
}
