package com.zhs.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhs.common.entity.Classify;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ClassifyMapper extends BaseMapper<Classify> {
}
