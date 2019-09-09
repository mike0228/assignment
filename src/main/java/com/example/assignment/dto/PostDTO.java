package com.example.assignment.dto;

import com.example.assignment.model.User;
import lombok.Data;

@Data
public class PostDTO {
    private Long id;
    private String title;
    private String description;
    private String tag;
    private long gmtCreate;
    private long gmtModified;
    private Long creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private User user;
}
