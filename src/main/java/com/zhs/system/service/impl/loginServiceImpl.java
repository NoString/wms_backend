package com.zhs.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhs.system.entity.UserLogin;
import com.zhs.system.mapper.UserLoginMapper;
import com.zhs.system.service.LoginService;
import org.springframework.stereotype.Service;

@Service
public class loginServiceImpl extends ServiceImpl<UserLoginMapper, UserLogin> implements LoginService {
}
