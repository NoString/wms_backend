package com.zhs.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhs.system.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
}
