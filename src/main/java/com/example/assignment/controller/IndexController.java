package com.example.assignment.controller;

import com.example.assignment.dto.PostInDTO;
import com.example.assignment.mapper.UserMapper;
import com.example.assignment.model.User;
import com.example.assignment.service.PostInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController
{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PostInService postInService;
    @GetMapping({"/","/index"})
    public String index(HttpServletRequest request, Model model)
    {
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length !=0)
        for(Cookie cookie : cookies)
        {
            if(cookie.getName().equals("token"))
            {
                String token = cookie.getValue();
                User user = userMapper.searchByToken(token);
                if(user != null)
                {
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }

        List<PostInDTO> postInsList = postInService.list();
        model.addAttribute("postIns",postInsList);
        return "index";
    }
}
