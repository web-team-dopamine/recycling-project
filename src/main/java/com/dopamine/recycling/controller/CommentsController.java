package com.dopamine.recycling.controller;

import com.dopamine.recycling.domain.dto.CommentsRequestDto;
import com.dopamine.recycling.domain.entity.Comments;
import com.dopamine.recycling.security.CustomUserDetails;
import com.dopamine.recycling.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentsController {
    private final CommentsService commentsService;

    @GetMapping("")
    public String getComments(@RequestParam(value="postId") Long postId,
                                      @AuthenticationPrincipal CustomUserDetails userDetails,
                                      Model model) {
        Long userId = userDetails.getUserId();

        List<Comments> commentsList = commentsService.getComments(postId, userId);

        model.addAttribute("comments", commentsList);
        model.addAttribute("userId", userId);

        return "post/comment";
    }

    @PostMapping("/add")
    public String addNewComment(@RequestBody CommentsRequestDto request,
                                @AuthenticationPrincipal CustomUserDetails userDetails,
                                Model model) {
        Long userId = userDetails.getUserId();
        Long postId = request.getPostId();

        request.setUserId(userId);
        request.setPostId(postId);
        request.setLikesCount(0L);

        commentsService.addComment(request);

        List<Comments> commentsList = commentsService.getComments(request.getPostId(), request.getUserId());
        model.addAttribute("comments", commentsList);
        model.addAttribute("userId", userId);

        return "post/comment";
    }

    @PutMapping("/modify")
    public String updateComment(@RequestBody CommentsRequestDto request,
                                @AuthenticationPrincipal CustomUserDetails userDetails,
                                Model model) {
        Long userId = userDetails.getUserId();
        Long writerId = commentsService.getCommentWriterId(request.getId());

        if(userId.equals(writerId)) {

            List<Comments> commentsList = commentsService.updateComment(request.getPostId(), userId, request.getContent());
            model.addAttribute("comments", commentsList);
            model.addAttribute("userId", userId);

            return "post/comment";
        }

        model.addAttribute("error", "You are not authorized to update this comment.");
        return null;
    }

    @DeleteMapping("/delete")
    public String deleteComment(@RequestBody CommentsRequestDto request,
                                @AuthenticationPrincipal CustomUserDetails userDetails,
                                Model model) {
        Long userId = userDetails.getUserId();
        Long writerId = commentsService.getCommentWriterId(request.getId());

        if(userId.equals(writerId)) {
            List<Comments> commentsList = commentsService.deleteComment(request.getId());
            model.addAttribute("comments", commentsList);
            model.addAttribute("userId", userId);

            return "post/comment";
        }

        model.addAttribute("error", "You are not authorized to delete this comment.");
        return null;
    }

    @PostMapping("/like")
    public ResponseEntity<?> likeComment(@AuthenticationPrincipal CustomUserDetails userDetails,
                                         @RequestParam(value = "commentId") Long commentId) {
        return ResponseEntity.ok(commentsService.addCommentLike(userDetails.getUserId(), commentId));
    }

    @DeleteMapping("/like/cancel")
    public ResponseEntity<?> cancelLikeComment(@AuthenticationPrincipal CustomUserDetails userDetails,
                                               @RequestParam(value = "commentId") Long commentId) {
        return ResponseEntity.ok(commentsService.cancelCommentLike(userDetails.getUserId(), commentId));
    }
}
