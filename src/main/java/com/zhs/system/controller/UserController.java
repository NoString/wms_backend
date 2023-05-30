package com.zhs.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhs.system.config.BaseController;
import com.zhs.system.entity.User;
import com.zhs.system.mapper.UserMapper;
import com.zhs.system.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static com.zhs.system.utils.Constant.*;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserMapper userMapper;



    @PostMapping("/login")
    public R login(@RequestBody Map<String,Object> params){

        User user = userMapper.selectOne(new QueryWrapper<User>()
                .eq("username", params.get("username")));
        if (user == null) {
            return R.parse(USER_10001);
        }
        if (!user.getPassword().equals(params.get("password"))){
            return R.parse(USER_10003);
        }else if (!user.isStatus()){
            return R.parse(USER_10002);
        }
        user.setPassword("");

        return R.ok(user);
    }
}
