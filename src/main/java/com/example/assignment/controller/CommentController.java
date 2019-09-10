package com.example.assignment.controller;

import com.example.assignment.dto.CommentCreateDTO;
import com.example.assignment.dto.CommentDTO;
import com.example.assignment.dto.ResultDTO;
import com.example.assignment.enums.CommentTypeEnum;
import com.example.assignment.exception.CustomizeErrorCode;
import com.example.assignment.model.Comment;
import com.example.assignment.model.User;
import com.example.assignment.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                       HttpServletRequest request){
            User user = (User) request.getSession().getAttribute("user");
            if (user == null){
                return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
            }
            if(commentCreateDTO == null || StringUtils.isBlank(commentCreateDTO.getContent())) {
                return ResultDTO.errorOf(CustomizeErrorCode.CONTENT_IS_EMPTY);
            }
            Comment comment = new Comment();
            comment.setParentId(commentCreateDTO.getParentId());
            comment.setContent(commentCreateDTO.getContent());
            comment.setType(commentCreateDTO.getType());
            comment.setGmtModified(System.currentTimeMillis());
            comment.setGmtCreate(System.currentTimeMillis());
            comment.setCommentator(user.getId());
            comment.setLikeCount(0L);
            comment.setCommentCount(0);
            commentService.insert(comment);
            return ResultDTO.okOf();
    }
    @ResponseBody
    @RequestMapping(value = "/comment/{id}",method = RequestMethod.GET)
    public ResultDTO comments(@PathVariable(name = "id") Long id){
        List<CommentDTO> commentDTOS = commentService.listByTargetId(id, CommentTypeEnum.COMMENT);
        return ResultDTO.okOf(commentDTOS);
    }
}
