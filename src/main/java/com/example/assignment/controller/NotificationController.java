package com.example.assignment.controller;

import com.example.assignment.dto.NotificationDTO;
import com.example.assignment.dto.PaginationDTO;
import com.example.assignment.enums.NotificationTypeEnum;
import com.example.assignment.model.User;
import com.example.assignment.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notification/{id}")
    public String profile(HttpServletRequest request,
                          @PathVariable(name = "id" )Long id){
        User user = (User) request.getSession().getAttribute("user" );

        if(user==null){
            return "redirect:/";
        }
        NotificationDTO notificationDTO = notificationService.read(id,user);
        if (NotificationTypeEnum.REPLY_COMMENT.getType() == notificationDTO.getType()||
                NotificationTypeEnum.REPLY_POST.getType() == notificationDTO.getType()) {
                return "redirect:/post/"+notificationDTO.getOuterid();
        }else {
            return "redirect:/";
        }
    }
}
