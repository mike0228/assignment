package com.example.assignment.controller;

import com.example.assignment.dto.AccessTokenDTO;
import com.example.assignment.dto.GithubUser;
import com.example.assignment.model.User;
import com.example.assignment.service.UserService;
import com.example.assignment.support.GithubSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
    private UserService userService;
    @GetMapping("/callback")
    public  String callback(@RequestParam(name="code")String code ,
                            @RequestParam(name="state")String state,
                            HttpServletResponse response) {
        AccessTokenDTO accesstoken =new AccessTokenDTO();
        accesstoken.setClient_id(clientId);
        accesstoken.setCode(code);
        accesstoken.setRedirect_uri(redirectUri);
        accesstoken.setState(state);
        accesstoken.setClient_secret(clientSecret);
        String accessToken = githubSupport.getAccessToken(accesstoken);
        GithubUser githubUser = githubSupport.getUser(accessToken);
        if(githubUser != null && githubUser.getId() != null)
        {
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setAvatarUrl(githubUser.getAvatarUrl());
            user.setBio(githubUser.getBio());
            user.setIsAdministrator(false);
            userService.createOrUpdate(user);
            response.addCookie(new Cookie("token",token));
            return "redirect:/";
        }
        else
        {
            return "redirect:/";
        }
        }
        @GetMapping("/logout")
        public String logout(HttpServletRequest request ,HttpServletResponse response){
            request.getSession().removeAttribute("user");
            Cookie cookie = new Cookie("token",null);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            return "redirect:/";
    }
}
