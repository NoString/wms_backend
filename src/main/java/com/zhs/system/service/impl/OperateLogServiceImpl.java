package com.zhs.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhs.system.entity.OperateLog;
import com.zhs.system.mapper.OperateLogMapper;
import com.zhs.system.service.OperateLogService;
import org.springframework.stereotype.Service;

@Service
public class OperateLogServiceImpl extends ServiceImpl<OperateLogMapper, OperateLog> implements OperateLogService {
}
