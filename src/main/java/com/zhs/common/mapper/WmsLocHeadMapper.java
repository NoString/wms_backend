package com.zhs.common.mapper;

import com.zhs.common.entity.WmsLocHead;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhs.common.entity.param.ChartParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ZHU HANGSHUAI
 * @since 2023-11-22
 */
@Mapper
public interface WmsLocHeadMapper extends BaseMapper<WmsLocHead> {

    @Select("SELECT loc_sts AS name, Count(loc_sts) AS value FROM `wms_loc_head` GROUP BY loc_sts")
    ArrayList<ChartParam> groupBySts();
}
