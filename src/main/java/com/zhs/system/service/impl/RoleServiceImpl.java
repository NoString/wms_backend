package com.zhs.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhs.system.entity.Role;
import com.zhs.system.entity.Users;
import com.zhs.system.mapper.RoleMapper;
import com.zhs.system.mapper.UsersMapper;
import com.zhs.system.service.RoleService;
import com.zhs.system.service.UsersService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
}
