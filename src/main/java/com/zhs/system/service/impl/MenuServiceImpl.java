package com.zhs.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhs.system.entity.Menu;
import com.zhs.system.entity.Users;
import com.zhs.system.mapper.MenuMapper;
import com.zhs.system.mapper.UsersMapper;
import com.zhs.system.service.MenuService;
import com.zhs.system.service.UsersService;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
}
