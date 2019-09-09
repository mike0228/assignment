package com.example.assignment.controller;

import com.example.assignment.dto.CommentCreateDTO;
import com.example.assignment.dto.CommentDTO;
import com.example.assignment.dto.PostDTO;
import com.example.assignment.service.CommentService;
import com.example.assignment.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/post/{id}")
    public String post(@PathVariable(name = "id") Long id, Model model){
        PostDTO postDTO = postService.getById(id);
        List<CommentDTO> comments= commentService.listByPostId(id);
        postService.incView(id);
        model.addAttribute("post", postDTO);
        model.addAttribute("comments", comments);
        return "post";
    }
}
