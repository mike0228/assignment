package com.example.assignment.mapper;

import com.example.assignment.model.Post;

public interface PostExtMapper {
    int incView(Post record);
    int incCommentCount(Post record);
}
