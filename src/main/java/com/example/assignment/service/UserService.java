package com.example.assignment.service;

import com.example.assignment.mapper.AdminMapper;
import com.example.assignment.mapper.UserMapper;
import com.example.assignment.model.Admin;
import com.example.assignment.model.AdminExample;
import com.example.assignment.model.User;
import com.example.assignment.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AdminMapper adminMapper;


    public void createOrUpdate(User user) {
        UserExample userExample = new UserExample();
        AdminExample adminExample = new AdminExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        adminExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        if (users.size() == 0){
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else {
            User dbUser = users.get(0);
            User updateUser = new User();
            if(admins.size() == 0){
                updateUser.setIsAdministrator(false);
            }else{
                updateUser.setIsAdministrator(true);
            }
            updateUser.setGmtModified(System.currentTimeMillis());
            updateUser.setAvatarUrl(user.getAvatarUrl());
            updateUser.setName(user.getName());
            updateUser.setToken(user.getToken());
            UserExample example = new UserExample();
            example.createCriteria().andIdEqualTo(dbUser.getId());
            userMapper.updateByExampleSelective(updateUser,example);
        }
    }
}
