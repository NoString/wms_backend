package com.zhs.common.mapper;

import com.zhs.common.entity.WmsTailOutRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

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
}
