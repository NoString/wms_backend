package com.zhs.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2023-11-22
 */
@Getter
@Setter
@TableName("wms_loc_tail")
public class WmsLocTail implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("loc_no")
    private String locNo;

    @TableField("m_name")
    private String mName;

    @TableField("m_code")
    private String mCode;

    @TableField("m_classify_name")
    private String mClassifyName;

    @TableField("m_expired_day")
    private LocalDateTime mExpiredDay;

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

    @TableField(exist = false)
    private String key;

    public void setId(Long id) {
        if (id != null){
            this.key = String.valueOf(id);
            this.id = id;
        }
    }
}
