package com.buezman.fashionblog.controllers;


import com.buezman.fashionblog.dto.PostDto;
import com.buezman.fashionblog.models.Comment;
import com.buezman.fashionblog.models.Post;
import com.buezman.fashionblog.services.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostDto> viewAllPosts() {
        return postService.getAllPosts();
    }

    @PostMapping("add/{categoryId}")
    public ResponseEntity<PostDto> addNewPost(@RequestBody Post post, @PathVariable Long categoryId) {
        return postService.createPost(post, categoryId);
    }

    @GetMapping("{id}")
    public ResponseEntity<PostDto> viewPostById(@PathVariable Long id) {
        return postService.viewPostById(id);
    }

    @GetMapping("{postId}/comments")
    public List<Comment> viewAllPostComments(@PathVariable Long postId) {
        return postService.viewAllPostComments(postId);
    }

    @GetMapping("/search/{searchWord}")
    public List<PostDto> searchForPost(@PathVariable String searchWord) {
        //System.out.println("in search for " + title);
        return postService.searchPost(searchWord);
    }

    @PutMapping("{id}")
    public ResponseEntity<Post> editPost(@PathVariable Long id, @RequestBody Post post) {
        return postService.editPost(id, post);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Post> deletePostById(@PathVariable Long id) {
        return postService.deletePost(id);
    }
}
