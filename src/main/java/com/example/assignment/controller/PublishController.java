package com.example.assignment.controller;

import com.example.assignment.mapper.PostingMapper;
import com.example.assignment.mapper.UserMapper;
import com.example.assignment.model.Posting;
import com.example.assignment.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private PostingMapper postingMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            HttpServletRequest request,
            Model model) {
        User user = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();
                user = userMapper.searchByToken(token);
                if (user != null) {
                    request.getSession().setAttribute("user", user);
                }
                break;
            }
        }
        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }
        Posting posting = new Posting();
        posting.setTitle(title);
        posting.setDescription(description);
        posting.setTag(tag);
        posting.setCreator(user.getId());
        posting.setGmtCreate(System.currentTimeMillis());
        posting.setGmtModified(posting.getGmtCreate());
        postingMapper.create(posting);
        return "redirect:/";
    }
}
