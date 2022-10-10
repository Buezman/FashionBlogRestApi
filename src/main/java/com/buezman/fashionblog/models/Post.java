package com.buezman.fashionblog.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "posts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;
    private String title;
    private String body;
    private String image;
    @ManyToOne
    @JoinColumn(name = "category_category_id")
    private Category category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long commentsCount;
    private Long likesCount;
}
