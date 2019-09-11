package com.example.assignment.controller;

import com.example.assignment.dto.PaginationDTO;
import com.example.assignment.model.Notification;
import com.example.assignment.model.User;
import com.example.assignment.service.NotificationService;
import com.example.assignment.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProfileController {
    @Autowired
    private PostService postService;
    @Autowired
    private NotificationService notificationService;
    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest request,
                          @PathVariable(name = "action" )String action ,
                          @RequestParam(name = "page",defaultValue = "1")Integer page,
                          @RequestParam(name = "size",defaultValue = "5")Integer size,
                          Model model){
        User user = (User) request.getSession().getAttribute("user" );

        if(user==null){
            return "redirect:/";
        }
        if ("posts".equals(action)){
            model.addAttribute("section","posts");
            model.addAttribute("sectionName","我的提问");
            PaginationDTO paginationDTO = postService.list(user.getId(),page,size);
            model.addAttribute("pagination",paginationDTO);
        }
        else if ("replies".equals(action)){
            PaginationDTO paginationDTO = notificationService.list(user.getId(),page,size);
            model.addAttribute("pagination",paginationDTO);
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }
        return "profile";
    }
}
