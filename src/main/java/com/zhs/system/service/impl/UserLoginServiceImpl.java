package com.zhs.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhs.system.entity.User;
import com.zhs.system.entity.UserLogin;
import com.zhs.system.mapper.UserLoginMapper;
import com.zhs.system.mapper.UserMapper;
import com.zhs.system.service.UserLoginService;
import com.zhs.system.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImpl extends ServiceImpl<UserLoginMapper, UserLogin> implements UserLoginService {
}
