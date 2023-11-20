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
 * @since 2023-11-20
 */
@Getter
@Setter
@TableName("wms_material")
public class WmsMaterial implements Serializable {

    private static final long serialVersionUID = 1L;

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
    @TableField("classify_id")
    private Long classifyId;

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
    private LocalDateTime createTime;

    /**
     * create user
     */
    @TableField("create_by")
    private Long createBy;

    /**
     * update time
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * update user
     */
    @TableField("update_by")
    private Long updateBy;


}
