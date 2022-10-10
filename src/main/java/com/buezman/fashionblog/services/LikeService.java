package com.buezman.fashionblog.services;


import org.springframework.http.ResponseEntity;

public interface LikeService {
    ResponseEntity<String> likeOrUnlikePost(Long userId, Long postId);

}
