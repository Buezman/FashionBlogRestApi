package com.buezman.fashionblog.services.implementations;

import com.buezman.fashionblog.exception.ResourceNotFoundException;
import com.buezman.fashionblog.models.Like;
import com.buezman.fashionblog.models.Post;
import com.buezman.fashionblog.models.User;
import com.buezman.fashionblog.repositories.LikeRepository;
import com.buezman.fashionblog.repositories.PostRepository;
import com.buezman.fashionblog.repositories.UserRepository;
import com.buezman.fashionblog.services.LikeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class LikeServiceImpl implements LikeService {

    private UserRepository userRepo;
    private PostRepository postRepo;
    private LikeRepository likeRepo;


    @Override
    public ResponseEntity<String> likeOrUnlikePost(Long userId, Long postId) {
        User user = userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("user with id " + userId + " not found" ));
        Post post = postRepo.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("post with id " + postId + " not found"));
        Like like = likeRepo.findLikeByPostIdAndUserId(postId, userId);
        if (like != null) {
            likeRepo.delete(like);
            post.setLikesCount(post.getLikesCount() - 1L);

            return new ResponseEntity<>("post unliked", HttpStatus.ACCEPTED);
        }
        Like newLike = new Like();
        newLike.setPostId(postId);
        newLike.setUserId(userId);
        newLike.setLikedBy(user.getName());
        newLike.setLikedAt(LocalDateTime.now());

        post.setLikesCount(post.getLikesCount() + 1L);

        likeRepo.save(newLike);

        return new ResponseEntity<>("Like successful", HttpStatus.ACCEPTED);
    }

}
