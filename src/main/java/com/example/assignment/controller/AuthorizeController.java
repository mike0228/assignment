package com.example.assignment.controller;

import com.example.assignment.dto.AccessToken;
import com.example.assignment.dto.GithubUser;
import com.example.assignment.mapper.UserMapper;
import com.example.assignment.model.User;
import com.example.assignment.support.GithubSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubSupport githubSupport;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserMapper userMapper;
    @GetMapping("/callback")
    public  String callback(@RequestParam(name="code")String code , @RequestParam(name="state")String state, HttpServletResponse response) {
        AccessToken accesstoken =new AccessToken();
        accesstoken.setClient_id(clientId);
        accesstoken.setCode(code);
        accesstoken.setRedirect_uri(redirectUri);
        accesstoken.setState(state);
        accesstoken.setClient_secret(clientSecret);
        String accessToken = githubSupport.getAccessToken(accesstoken);
        GithubUser githubUser = githubSupport.getUser(accessToken);
        if(githubUser != null)
        {
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
            response.addCookie(new Cookie("token",token));
            return "redirect:index";
        }
        else
        {
            return "redirect:index";
        }
    }
}
