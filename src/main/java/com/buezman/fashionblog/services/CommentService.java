package com.buezman.fashionblog.services;

import com.buezman.fashionblog.models.Comment;
import org.springframework.http.ResponseEntity;

public interface CommentService {
    ResponseEntity<Comment> commentOnPost(Long id, Long postId, Comment comment);
    ResponseEntity<Comment> deleteComment(Long commentId, Long postId);
}
