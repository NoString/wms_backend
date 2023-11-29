package com.zhs.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author ZHU HANGSHUAI
 * @since 2023-11-29
 */
@Getter
@Setter
@TableName("wms_tail_in_record")
public class WmsTailInRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    @TableField("qty")
    private Double qty;

    @TableField("loc_no")
    private String locNo;

    @TableField("m_id")
    private Long mId;

    @TableField("m_name")
    private String mName;

    @TableField("m_code")
    private String mCode;

    @TableField("m_classify_name")
    private String mClassifyName;

    @TableField("m_expired_date")
    private LocalDateTime mExpiredDate;

    @TableField("m_weight")
    private Double mWeight;

    @TableField("m_color")
    private String mColor;

    @TableField("m_unit")
    private String mUnit;

    @TableField("m_memo")
    private String mMemo;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("create_by")
    private String createBy;

    @TableField("update_time")
    private LocalDateTime updateTime;

    @TableField("update_by")
    private String updateBy;


}
