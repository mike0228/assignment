package com.example.assignment.controller;

import com.example.assignment.cache.TagCache;
import com.example.assignment.dto.PostDTO;
import com.example.assignment.mapper.PostMapper;
import com.example.assignment.mapper.UserMapper;
import com.example.assignment.model.Post;
import com.example.assignment.model.User;
import com.example.assignment.model.UserExample;
import com.example.assignment.service.PostService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private PostService postService;

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id")Long id ,
                       Model model){
        PostDTO post= postService.getById(id);
        model.addAttribute("title", post.getTitle());
        model.addAttribute("tag", post.getTag());
        model.addAttribute("description", post.getDescription());
        model.addAttribute("id",post.getId());
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish(Model model) {
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "tag", required = false) String tag,
            @RequestParam(value = "id", required = false)Long id,
            HttpServletRequest request,
            Model model) {
        model.addAttribute("title", title);
        model.addAttribute("tag", tag);
        model.addAttribute("description", description);
        model.addAttribute("tags", TagCache.get());
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
        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }
        if (title == null || title == "") {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (description == null || description == "") {
            model.addAttribute("error", "内容不能为空");
            return "publish";
        }
        String invalid = TagCache.filterInvalid(tag);
        if (StringUtils.isNotBlank(invalid)){
            model.addAttribute("error","非法标签："+invalid);
            return  "publish";
        }
        if (tag == null || tag == "") {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }
        Post post = new Post();
        post.setTitle(title);
        post.setDescription(description);
        post.setTag(tag);
        post.setCreator(user.getId());
        post.setId(id);
        postService.createOrUpdate(post);
        return "redirect:/";
    }
}
