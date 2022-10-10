package com.buezman.fashionblog.repositories;

import com.buezman.fashionblog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findPostsByTitleContaining(String title);
    List<Post> findAllByTitleContaining(String search);
}
