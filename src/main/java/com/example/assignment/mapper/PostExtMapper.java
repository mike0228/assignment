package com.example.assignment.mapper;

import com.example.assignment.dto.PostQueryDTO;
import com.example.assignment.model.Post;

import java.util.List;

public interface PostExtMapper {
    int incView(Post record);
    int incCommentCount(Post record);
    List<Post> selectRelated(Post post);

    Integer countBySearch(PostQueryDTO postQueryDTO);

    List<Post> selectBySearch(PostQueryDTO postQueryDTO);
}
