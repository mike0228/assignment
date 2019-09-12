package com.example.assignment.controller;

import com.example.assignment.dto.PaginationDTO;
import com.example.assignment.dto.PostDTO;
import com.example.assignment.model.Post;
import com.example.assignment.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController
{
    @Autowired
    private PostService postService;
    @GetMapping({"/"})
    public String index(HttpServletRequest request, Model model,
                        @RequestParam(name = "page",defaultValue = "1")Integer page,
                        @RequestParam(name = "size",defaultValue = "8")Integer size,
                        @RequestParam(name = "search",required = false)String search
    ) {
        PaginationDTO pagination = postService.list(search,page,size);
        List<PostDTO> hotTopics = postService.listHotTopics();
        model.addAttribute("pagination",pagination);
        model.addAttribute("search",search);
        model.addAttribute("hotTopics",hotTopics);
        return "index";
    }
}
