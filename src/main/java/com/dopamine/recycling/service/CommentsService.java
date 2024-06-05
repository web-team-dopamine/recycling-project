package com.dopamine.recycling.service;

import com.dopamine.recycling.domain.dto.CommentsRequestDto;
import com.dopamine.recycling.domain.entity.Comments;
import com.dopamine.recycling.repository.CommentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentsService {

    private final CommentsRepository commentsRepository;

    public List<Comments> getComments(Long postId, Long userId) {
        return commentsRepository.getComments(postId, userId);
    }

    public void addComment(CommentsRequestDto request) {
        Comments comment = request.toEntity(request.getPostId(), request.getUserId());
        commentsRepository.save(comment);
    }

    public List<Comments> updateComment(Long postId, Long userId, String content) {
        int updatedCount = commentsRepository.updateComment(postId, userId, content);
        if (updatedCount > 0) {
            return commentsRepository.getComments(postId, userId);
        }
        return Collections.emptyList();
    }

    public Long getCommentWriterId(Long id) {
        return commentsRepository.findCommentWriterId(id);
    }

    public List<Comments> deleteComment(Long id) {
        commentsRepository.deleteComment(id);

        Comments comment = commentsRepository.findById(id).orElse(null);
        if (comment != null) {
            return commentsRepository.findAllByPostId(comment.getPostId());
        }
        return Collections.emptyList();
    }
}
