package com.zhs.common.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhs.system.entity.Users;
import com.zhs.system.service.UsersService;
import com.zhs.system.utils.SpringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

@TableName("wms_material")
public class Material {

    @TableId(value = "key", type = IdType.AUTO)
    private Long key;

    private String name;

    private String code;

    @TableField("classify_id")
    private Long classifyId;

    @TableField("expired_day")
    private Integer expiredDay;

    private Double weight;

    private String color;

    private String unit;

    private String memo;

    @TableField("create_time")
    private Date createTime;

    @TableField("create_by")
    private Long createBy;

    @TableField("update_time")
    private Date updateTime;

    @TableField("update_by")
    private Long updateBy;

    @TableField(exist = false)
    private String createTime$;

    @TableField(exist = false)
    private String createBy$;

    @TableField(exist = false)
    private String updateTime$;

    @TableField(exist = false)
    private String updateBy$;

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd-MM-yyyy HH:mm:ss");
        this.createTime$ = dateFormat.format(createTime);
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
        UsersService bean = SpringUtils.getBean(UsersService.class);
        Users byId = bean.getById(createBy);
        this.createBy$ = byId.getNickname();
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd-MM-yyyy HH:mm:ss");
        this.updateTime$ = dateFormat.format(updateTime);
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
        UsersService bean = SpringUtils.getBean(UsersService.class);
        Users byId = bean.getById(updateBy);
        this.updateBy$ = byId.getNickname();
    }

}
