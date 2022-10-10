package com.buezman.fashionblog.services.implementations;

import com.buezman.fashionblog.dto.PostDto;
import com.buezman.fashionblog.exception.ResourceNotFoundException;
import com.buezman.fashionblog.models.Category;
import com.buezman.fashionblog.models.Comment;
import com.buezman.fashionblog.models.Like;
import com.buezman.fashionblog.models.Post;
import com.buezman.fashionblog.repositories.CommentRepository;
import com.buezman.fashionblog.repositories.LikeRepository;
import com.buezman.fashionblog.repositories.PostRepository;
import com.buezman.fashionblog.services.CategoryService;
import com.buezman.fashionblog.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final CategoryService categoryService;
    private final LikeRepository likeRepository;

    public PostServiceImpl(PostRepository postRepository, CommentRepository commentRepository, CategoryService categoryService, LikeRepository likeRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.categoryService = categoryService;
        this.likeRepository = likeRepository;
    }

    @Override
    public ResponseEntity<PostDto> createPost(Post post, Long categoryId) {
        Category category = categoryService.findCategoryById(categoryId);
        if (category == null)
            throw new ResourceNotFoundException("category does not exist, create new category for this post");

        post.setCategory(category);
        post.setCreatedAt(LocalDateTime.now());
        post.setCommentsCount(0L);
        post.setLikesCount(0L);

        postRepository.save(post);

        return getPostDtoResponseEntity(post);
    }

    private ResponseEntity<PostDto> getPostDtoResponseEntity(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setBody(post.getBody());
        postDto.setCommentsCount(post.getCommentsCount());
        postDto.setLikesCount(post.getLikesCount());

        return ResponseEntity.ok(postDto);
    }

    @Override
    public ResponseEntity<PostDto> viewPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("post with id " + id + " not found"));
        return getPostDtoResponseEntity(post);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        List<PostDto> result = new ArrayList<>();
        for (Post post : posts) {
            getPostDto(result, post);
        }
        return result;
    }

    @Override
    public List<PostDto> searchPost(String search) {
        search = search.toLowerCase();
        List<Post> allPosts = postRepository.findAll();
        List<PostDto> result = new ArrayList<>();
        for (Post post : allPosts) {
            String title = post.getTitle().toLowerCase();
            if (title.contains(search)){
                getPostDto(result, post);
            }
        }
        return result;
    }

    private void getPostDto(List<PostDto> result, Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setCategory(post.getCategory().getName());
        postDto.setBody(post.getBody());
        postDto.setTitle(post.getTitle());
        postDto.setImage(post.getImage());
        postDto.setLikesCount(post.getLikesCount());
        postDto.setCommentsCount(post.getCommentsCount());

        result.add(postDto);
    }

    @Override
    public ResponseEntity<Post> editPost(Long id, Post post) {
        Post updatedPost = postRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("post with id " + id + " not found"));
        updatedPost.setTitle(post.getTitle());
        updatedPost.setBody(post.getBody());
        updatedPost.setUpdatedAt(LocalDateTime.now());
        updatedPost.setImage(post.getImage());

        postRepository.save(updatedPost);
        return ResponseEntity.ok(updatedPost);
    }

    @Override
    public List<Comment> viewAllPostComments(Long postId) {
        return commentRepository.findCommentsByPostId(postId);
    }

    @Override
    public ResponseEntity<Post> deletePost(Long id) {
        Post postToDelete = postRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("post with id " + id + " not found"));
        List<Comment> postComments = commentRepository.findCommentsByPostId(id);
        List<Like> postLikes = likeRepository.findLikesByPostId(id);
        commentRepository.deleteAll(postComments);
        likeRepository.deleteAll(postLikes);
        postRepository.delete(postToDelete);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
