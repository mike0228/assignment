package com.example.assignment.service;

import com.example.assignment.enums.CommentTypeEnum;
import com.example.assignment.exception.CustomizeErrorCode;
import com.example.assignment.exception.CustomizeException;
import com.example.assignment.mapper.CommentMapper;
import com.example.assignment.mapper.PostExtMapper;
import com.example.assignment.mapper.PostMapper;
import com.example.assignment.model.Comment;
import com.example.assignment.model.Post;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private PostExtMapper postExtMapper;
    public void insert(Comment comment) {
        if (comment.getParentId() ==null || comment.getParentId() ==0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        if (comment.getType() == CommentTypeEnum.COMMENT.getType()) {
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (dbComment == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);
        }else {
            Post post = postMapper.selectByPrimaryKey(comment.getParentId());
            if (post == null){
                throw new CustomizeException(CustomizeErrorCode.POST_NOT_FOUND);
            }
            commentMapper.insert(comment);
            post.setCommentCount(1);
            postExtMapper.incCommentCount(post);
            }
    }
}
