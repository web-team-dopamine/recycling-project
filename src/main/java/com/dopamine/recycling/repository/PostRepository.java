package com.dopamine.recycling.repository;

import com.dopamine.recycling.domain.entity.Post;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    @Query("select p from  Post p where p.postType = :postType and p.isDeleted = false")
    List<Post> findByPostType(@Param("postType") String postType);


    @Modifying
    @Transactional
    @Query("update Post p set p.postType = :postType, p.title = :title, p.content = :content where p.id = :id ")
    void updatePostById(@Param("id") Long id, @Param("postType") String postType, @Param("title") String title, @Param("content") String content);
}
