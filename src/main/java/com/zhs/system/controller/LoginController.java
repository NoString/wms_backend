package com.zhs.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhs.system.config.BaseController;
import com.zhs.system.entity.Users;
import com.zhs.system.entity.UserLogin;
import com.zhs.system.service.LoginService;
import com.zhs.system.service.UsersService;
import com.zhs.system.utils.R;
import com.zhs.system.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.zhs.system.utils.Constant.*;

@RestController
@RequestMapping("/user")
public class LoginController extends BaseController {

    @Autowired
    private UsersService usersService;
    @Autowired
    private LoginService loginService;





    @PostMapping("/login")
    public R login(@RequestBody Map<String,Object> params){

        Users user = usersService.getOne(new QueryWrapper<Users>()
                .eq("username", params.get("username")));
        if (user == null) {
            return R.parse(USER_10001);
        }
        if (!user.getPassword().equals(params.get("password"))){
            return R.parse(USER_10003);
        }else if (!user.isStatus()){
            return R.parse(USER_10002);
        }
        UserLogin userLogin = new UserLogin();
        userLogin.setUserId(user.getId());
        userLogin.setToken(TokenUtils.token(user.getUsername(), user.getPassword()));
        userLogin.setCreateTime(new Date());
        loginService.saveOrUpdate(userLogin,new QueryWrapper<UserLogin>()
                .eq("user_id", user.getId()));
        Map<String, Object> res = new HashMap<>();
        res.put("username", user.getUsername());
        res.put("token", userLogin.getToken());
        res.put("nickname", user.getNickname());
        res.put("id",user.getId());
        return R.ok(res);
    }
}
