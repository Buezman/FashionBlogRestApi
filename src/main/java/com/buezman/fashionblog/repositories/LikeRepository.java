package com.buezman.fashionblog.repositories;

import com.buezman.fashionblog.models.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Like findLikeByPostIdAndUserId (Long postId, Long userId);

    List<Like> findLikesByPostId (Long postId);
}
