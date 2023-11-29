package com.zhs.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhs.system.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
public interface RoleMapper extends BaseMapper<Role> {
}
