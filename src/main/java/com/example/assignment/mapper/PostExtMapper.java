package com.example.assignment.mapper;

import com.example.assignment.model.Post;

import java.util.List;

public interface PostExtMapper {
    int incView(Post record);
    int incCommentCount(Post record);
    List<Post> selectRelated(Post post);
}
