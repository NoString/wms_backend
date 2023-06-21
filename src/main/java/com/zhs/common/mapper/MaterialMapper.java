package com.zhs.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhs.common.entity.Classify;
import com.zhs.common.entity.Material;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MaterialMapper extends BaseMapper<Material> {
}
