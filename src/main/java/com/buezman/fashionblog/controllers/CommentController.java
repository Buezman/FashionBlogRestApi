package com.buezman.fashionblog.controllers;

import com.buezman.fashionblog.models.Comment;
import com.buezman.fashionblog.services.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/posts")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("{postId}/comment/{userId}")
    public ResponseEntity<Comment> makeComment(@PathVariable Long userId, @PathVariable Long postId, @RequestBody Comment comment) {
        return commentService.commentOnPost(userId, postId, comment);
    }

    @DeleteMapping("{postId}/comment/{commentId}")
    public ResponseEntity<Comment> deleteComment(@PathVariable Long commentId, @PathVariable Long postId) {
        return commentService.deleteComment(commentId, postId);
    }
}
