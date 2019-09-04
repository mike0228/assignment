package com.example.assignment.controller;

import com.example.assignment.dto.AccessToken;
import com.example.assignment.dto.Guser;
import com.example.assignment.support.GithubSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubSupport githubSupport;
    @GetMapping("/backlocal")
    public  String backlocal(@RequestParam(name="code")String code , @RequestParam(name="state")String state) {
        AccessToken accesstoken =new AccessToken();
        accesstoken.setClient_id("8f3a6ee67cc571ebf8e6");
        accesstoken.setCode(code);
        accesstoken.setRedirect_uri("http://localhost:8080/backlocal");
        accesstoken.setState(state);
        accesstoken.setClient_secret("47a396e3fe2532dfd9572090f17522a16bbef55c");
        String token = githubSupport.getAccessToken(accesstoken);
        Guser user = githubSupport.gtuser(token);
        System.out.println(user.getName());
        return "index";
    }
}
