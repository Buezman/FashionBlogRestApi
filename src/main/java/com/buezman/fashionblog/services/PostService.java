package com.buezman.fashionblog.services;

import com.buezman.fashionblog.dto.PostDto;
import com.buezman.fashionblog.models.Comment;
import com.buezman.fashionblog.models.Post;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {
    ResponseEntity<PostDto> createPost(Post post, Long categoryId);
    ResponseEntity<PostDto> viewPostById(Long id);
    List<PostDto> getAllPosts();
    List<PostDto> searchPost(String search);
    List<Comment> viewAllPostComments(Long postId);
    ResponseEntity<Post> editPost(Long id, Post post);
    ResponseEntity<Post> deletePost(Long id);
}
