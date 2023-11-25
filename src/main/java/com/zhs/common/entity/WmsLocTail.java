package com.zhs.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author ZHU HANGSHUAI
 * @since 2023-11-22
 */
@Data
@TableName("wms_loc_tail")
public class WmsLocTail implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("loc_no")
    private String locNo;

    @TableField("m_name")
    private String name;

    @TableField("m_code")
    private String code;

    @TableField("m_classify_name")
    private String mClassifyName;

    @TableField("m_expired_date")
    private Date mExpiredDate;

    @TableField("m_weight")
    private Double mWeight;

    @TableField("m_color")
    private String mColor;

    @TableField("m_unit")
    private String mUnit;

    @TableField("m_memo")
    private String mMemo;

    @TableField("create_time")
    private Date createTime;

    @TableField("create_by")
    private String createBy;

    @TableField("update_time")
    private Date updateTime;

    @TableField("update_by")
    private String updateBy;

    @TableField("m_id")
    private Long materialId;

    private Float qty;

    @TableField(exist = false)
    private String key;

    public void setId(Long id) {
        if (id != null){
            this.key = String.valueOf(id);
            this.id = id;
        }
    }
}
