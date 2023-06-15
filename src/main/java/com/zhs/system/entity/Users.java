package com.zhs.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhs.system.service.RoleService;
import com.zhs.system.utils.SpringUtils;
import lombok.Data;

import javax.swing.text.Utilities;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@TableName("sys_user")
public class Users {

    @TableField(exist = false)
    private String key;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("host_id")
    private int hostId;

    @TableField("dept_id")
    private int deptId;

    @TableField("role_id")
    private Long roleId;

    @TableField("username")
    private String username;

    @TableField("nickname")
    private String nickname;

    @TableField("mobile")
    private String mobile;

    @TableField("password")
    private String password;

    @TableField("email")
    private String email;

    @TableField("gender")
    private boolean gender;

    @TableField("create_time")
    private Date createTime;

    @TableField("create_by")
    private Long createBy;

    @TableField("update_time")
    private Date updateTime;

    @TableField("update_by")
    private Long updateBy;

    @TableField("last_login")
    private Date lastLogin;

    @TableField("status")
    private boolean status;


    @TableField(exist = false)
    private String lastLogin$;

    @TableField(exist = false)
    private String role;

    public void setId(Long id) {
        this.key = String.valueOf(id);
        this.id = id;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd-MM-yyyy HH:mm:ss");
        this.lastLogin$ = dateFormat.format(lastLogin);
    }

    public void setRoleId(Long roleId){
        this.roleId = roleId;
        RoleService bean = SpringUtils.getBean(RoleService.class);
        Role role = bean.getById(roleId);
        this.role = role.getName();
    }
}