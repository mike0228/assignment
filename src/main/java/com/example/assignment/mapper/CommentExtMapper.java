package com.example.assignment.mapper;

import com.example.assignment.model.Comment;
import com.example.assignment.model.CommentExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
    int incLikeCount(Comment comment);
    int decLikeCount(Comment comment);
}