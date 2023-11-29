package com.zhs.common.mapper;

import com.zhs.common.entity.WmsTailOutRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhs.common.entity.param.PieParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
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
public interface WmsTailOutRecordMapper extends BaseMapper<WmsTailOutRecord> {

    @Insert("INSERT INTO wms_tail_out_record select * from wms_loc_tail where id = #{id}")
    void saveRecord(Long id);

    @Select("SELECT COUNT(*) AS count FROM wms_tail_out_record WHERE DATE(create_time) = #{date}")
    Integer countByDate(String recentSevenDay);

    @Select("SELECT COUNT(m_classify_name) AS 'value', m_classify_name AS 'name' FROM `wms_tail_out_record` GROUP BY m_classify_name")

    ArrayList<PieParam> groupByClassify();

    @Select("SELECT COUNT(create_by) AS 'value', create_by AS 'name' FROM `wms_tail_out_record` GROUP BY create_by")

    ArrayList<PieParam> groupByUsername();

}
