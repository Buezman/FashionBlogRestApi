package com.buezman.fashionblog.controllers;

import com.buezman.fashionblog.models.Like;
import com.buezman.fashionblog.services.LikeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/posts")
public class LikeController {

    private LikeService likeService;

    @PostMapping("{postId}/like/{userId}")
    public ResponseEntity<String> likeOrUnlikePost(@PathVariable Long userId, @PathVariable Long postId) {
        return likeService.likeOrUnlikePost(userId, postId);
    }



}
