package com.zhs.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhs.system.entity.Users;
import com.zhs.system.service.RoleService;
import com.zhs.system.service.UsersService;
import com.zhs.system.utils.SpringUtils;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@TableName("wms_classify")
@Data
public class Classify {

    @TableField(exist = false)
    private String key;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String name;

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
        Users byId = bean.getById(createBy);
        this.updateBy$ = byId.getNickname();
    }

    public void setId(Long id) {
        this.id = id;
        this.key = String.valueOf(id);
    }
}
