package com.buezman.fashionblog.services.implementations;

import com.buezman.fashionblog.exception.ResourceNotFoundException;
import com.buezman.fashionblog.models.Comment;
import com.buezman.fashionblog.models.Post;
import com.buezman.fashionblog.models.User;
import com.buezman.fashionblog.repositories.CommentRepository;
import com.buezman.fashionblog.repositories.PostRepository;
import com.buezman.fashionblog.repositories.UserRepository;
import com.buezman.fashionblog.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentServiceImpl implements CommentService {

    UserRepository userRepo;
    PostRepository postRepo;
    CommentRepository commentRepo;

    public CommentServiceImpl(UserRepository userRepo, PostRepository postRepo, CommentRepository commentRepo) {
        this.userRepo = userRepo;
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
    }



    @Override
    public ResponseEntity<Comment> commentOnPost(Long userId, Long postId, Comment comment) {
        User user = userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("user with id " + userId + " not found"));
        Post post = postRepo.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("post with id " + postId + " not found"));
        Comment newComment = new Comment();
        newComment.setBody(comment.getBody());
        newComment.setCreatedAt(LocalDateTime.now());
        newComment.setMadeBy(user.getName());
        newComment.setUserId(userId);
        newComment.setPostId(postId);
        post.setCommentsCount(post.getCommentsCount() + 1L);
        commentRepo.save(newComment);

        return ResponseEntity.ok(comment);
    }

    @Override
    public ResponseEntity<Comment> deleteComment(Long commentId, Long postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("post with id " + postId + " not found"));
        Comment comment = commentRepo.findById(commentId)
                .orElseThrow(()-> new ResourceNotFoundException("comment with id " + commentId + " not found"));
        post.setCommentsCount(post.getCommentsCount() - 1);
        commentRepo.delete(comment);

        return new ResponseEntity<>(HttpStatus.OK);

    }
}
