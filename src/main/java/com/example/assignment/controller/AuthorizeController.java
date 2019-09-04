package com.example.assignment.controller;

import com.example.assignment.dto.AccessToken;
import com.example.assignment.dto.Guser;
import com.example.assignment.support.GithubSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

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

    @GetMapping("/callback")
    public  String callback(@RequestParam(name="code")String code , @RequestParam(name="state")String state, HttpServletRequest request) {
        AccessToken accesstoken =new AccessToken();
        accesstoken.setClient_id(clientId);
        accesstoken.setCode(code);
        accesstoken.setRedirect_uri(redirectUri);
        accesstoken.setState(state);
        accesstoken.setClient_secret(clientSecret);
        String token = githubSupport.getAccessToken(accesstoken);
        Guser user = githubSupport.gtuser(token);
        if(user != null)
        {
            request.getSession().setAttribute("user",user);
            return "redirect:index";
        }
        else
        {
            return "redirect:index";
        }
    }
}
