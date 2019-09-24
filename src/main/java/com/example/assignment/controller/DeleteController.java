package com.example.assignment.controller;

import com.example.assignment.mapper.UserMapper;
import com.example.assignment.model.User;
import com.example.assignment.model.UserExample;
import com.example.assignment.service.CommentService;
import com.example.assignment.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class DeleteController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/delete/{id}")
    public String delete(
            @PathVariable(name = "id")Long id ,
            HttpServletRequest request){
        User user = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0)

            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    UserExample userExample = new UserExample();
                    userExample.createCriteria().andTokenEqualTo(token);
                    user = userMapper.selectByExample(userExample).get(0);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        postService.deleteById(id,user.getIsAdministrator());
        return "redirect:/";
    }

    @GetMapping("/deleteComment/{id}/{postId}")
    public String deleteComment(
            @PathVariable(name = "id")Long id ,
            HttpServletRequest request,
            @PathVariable(name="postId")Long postId){
        User user = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0)

            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    UserExample userExample = new UserExample();
                    userExample.createCriteria().andTokenEqualTo(token);
                    user = userMapper.selectByExample(userExample).get(0);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        commentService.deleteById(id,user.getIsAdministrator(),postId);
        return "redirect:/post/"+postId.toString();
    }
}
