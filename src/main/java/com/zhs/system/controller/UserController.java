package com.zhs.system.controller;

import com.zhs.system.config.BaseController;
import com.zhs.system.entity.User;
import com.zhs.system.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserMapper userMapper;


    @PostMapping("/test")
    public String testSql(){
        List<User> users = userMapper.selectList(null);
        System.out.println("users = " + users);
        return String.valueOf(users.size());
    }
}
