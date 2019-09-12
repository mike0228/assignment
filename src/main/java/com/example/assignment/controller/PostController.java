package com.example.assignment.controller;

import com.example.assignment.dto.CommentDTO;
import com.example.assignment.dto.CommentListDTO;
import com.example.assignment.dto.PostDTO;
import com.example.assignment.enums.CommentTypeEnum;
import com.example.assignment.service.CommentService;
import com.example.assignment.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/post/{id}")
    public String post(@PathVariable(name = "id") Long id, Model model,
                       @RequestParam(name = "page",defaultValue = "1")Integer page,
                       @RequestParam(name = "size",defaultValue = "5")Integer size){
        PostDTO postDTO = postService.getById(id);
        List<PostDTO> relatesPosts = postService.selectRelated(postDTO);
        CommentListDTO comments= commentService.list(id, CommentTypeEnum.POST,page,size);
        model.addAttribute("post", postDTO);
        model.addAttribute("comments", comments);
        model.addAttribute("relatedPosts",relatesPosts);
        return "post";
    }

}
