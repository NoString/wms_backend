package com.zhs.common.mapper;

import com.zhs.common.entity.WmsTailInRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhs.common.entity.param.ChartParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ZHU HANGSHUAI
 * @since 2023-11-29
 */
@Mapper
public interface WmsTailInRecordMapper extends BaseMapper<WmsTailInRecord> {

    @Insert("INSERT INTO wms_tail_in_record select * from wms_loc_tail where id = #{id}")
    void saveRecord(Long id);

    @Select("SELECT COUNT(*) AS count FROM wms_tail_in_record WHERE DATE(create_time) = #{date}")
    Integer countByDate(@Param("date") String recentSevenDay);

    @Select("SELECT COUNT(m_classify_name) AS 'value', m_classify_name AS 'name' FROM `wms_tail_in_record` GROUP BY m_classify_name")
    ArrayList<ChartParam> groupByClassify();
    @Select("SELECT COUNT(create_by) AS 'value', create_by AS 'name' FROM `wms_tail_in_record` GROUP BY create_by")

    ArrayList<ChartParam> groupByUsername();

}
