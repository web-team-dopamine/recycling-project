package com.dopamine.recycling.repository;

import com.dopamine.recycling.domain.entity.Comments;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<Comments,Long> {

    @Query("SELECT c FROM Comments c WHERE c.postId = :postId AND c.userId = :userId AND c.isDeleted = FALSE")
    List<Comments> getComments(@Param("postId") Long postId, @Param("userId") Long userId);

    @Transactional
    @Modifying
    @Query("UPDATE Comments c SET c.content = :content WHERE c.postId = :postId AND c.userId = :userId AND c.isDeleted = FALSE")
    int updateComment(@Param("postId") Long postId, @Param("userId") Long userId, @Param("content") String content);

    @Query("SELECT c.userId FROM Comments c WHERE c.id = :id AND c.isDeleted = FALSE")
    Long findCommentWriterId(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Comments c SET c.isDeleted = TRUE WHERE c.id = :id")
    void deleteComment(@Param("id") Long id);

    @Query("SELECT c FROM Comments c WHERE c.postId = :postId AND c.isDeleted = FALSE")
    List<Comments> findAllByPostId(@Param("postId") Long postId);

    @Transactional
    @Modifying
    @Query("UPDATE Comments c SET c.likesCount = c.likesCount + 1 WHERE c.id = :commentId AND c.userId = :userId")
    void addCommentLike(@Param("userId") Long userId, @Param("commentId") Long commentId);

    @Transactional
    @Modifying
    @Query("UPDATE Comments c SET c.likesCount = CASE WHEN c.likesCount > 0 THEN c.likesCount - 1 ELSE c.likesCount END WHERE c.id = :commentId AND c.userId = :userId")
    void cancelCommentLike(@Param("userId") Long userId, @Param("commentId") Long commentId);

    @Query("SELECT c.likesCount FROM Comments c WHERE c.id = :commentId")
    Integer likeCount(@Param("commentId") Long commentId);
}
