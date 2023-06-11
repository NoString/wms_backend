package com.zhs.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhs.system.entity.OperateLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OperateLogMapper extends BaseMapper<OperateLog> {
}
