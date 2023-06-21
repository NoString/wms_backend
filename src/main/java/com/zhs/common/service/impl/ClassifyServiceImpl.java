package com.zhs.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhs.common.entity.Classify;
import com.zhs.common.mapper.ClassifyMapper;
import com.zhs.common.service.ClassifyService;
import com.zhs.system.entity.Menu;
import com.zhs.system.mapper.MenuMapper;
import com.zhs.system.service.MenuService;
import org.springframework.stereotype.Service;

@Service
public class ClassifyServiceImpl extends ServiceImpl<ClassifyMapper, Classify> implements ClassifyService {
}
