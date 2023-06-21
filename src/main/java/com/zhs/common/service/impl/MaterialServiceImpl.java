package com.zhs.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhs.common.entity.Classify;
import com.zhs.common.entity.Material;
import com.zhs.common.mapper.ClassifyMapper;
import com.zhs.common.mapper.MaterialMapper;
import com.zhs.common.service.ClassifyService;
import com.zhs.common.service.MaterialService;
import org.springframework.stereotype.Service;

@Service
public class MaterialServiceImpl extends ServiceImpl<MaterialMapper, Material> implements MaterialService {
}
