package com.zhs.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author ZHU HANGSHUAI
 * @since 2023-11-20
 */
@Getter
@Setter
@TableName("wms_material")
public class WmsMaterial implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableField(exist = false)
    private String key;

    /**
     * unique id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * material name
     */
    @TableField("name")
    private String name;

    /**
     * material code
     */
    @TableField("code")
    private String code;

    /**
     * classify id
     */
    @TableField("classify_name")
    private String classifyName;

    /**
     * expired day
     */
    @TableField("expired_day")
    private Integer expiredDay;

    /**
     * weight
     */
    @TableField("weight")
    private Double weight;

    /**
     * color
     */
    @TableField("color")
    private String color;

    /**
     * unit
     */
    @TableField("unit")
    private String unit;

    /**
     * memo
     */
    @TableField("memo")
    private String memo;

    /**
     * create time
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * create user
     */
    @TableField("create_by")
    private String createBy;

    /**
     * update time
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * update user
     */
    @TableField("update_by")
    private String updateBy;

    public void setId(Long id) {
        if (id != null){
            this.key = String.valueOf(id);
            this.id = id;
        }
    }

}
