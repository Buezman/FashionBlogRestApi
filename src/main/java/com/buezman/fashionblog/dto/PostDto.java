package com.buezman.fashionblog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {
    private Long id;
    private String title;
    private String body;
    private String image;
    private String category;
    private Long commentsCount;
    private Long likesCount;
}
