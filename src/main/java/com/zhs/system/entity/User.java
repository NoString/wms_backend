package com.zhs.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhs.system.config.UpdateValidateGroup;
import com.zhs.system.service.RoleService;
import com.zhs.system.service.UsersService;
import com.zhs.system.utils.DateFormat;
import com.zhs.system.utils.SpringUtils;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@TableName("sys_user")
public class User {


    @TableField(exist = false)
    private String key;

    //非string用Notnull, id在数据库是自增长, 添加的时候可以为null,但修改的时候不能为not, groups的作用就是区分添加和修改
    @NotNull(message = "id is null", groups = {UpdateValidateGroup.class})
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("host_id")
    private int hostId;

    @TableField("dept_id")
    private int deptId;


    @TableField("role_id")
    private Long roleId;

    @Size(min = 4,max = 30)
    @NotBlank(message = "username is null")
    @TableField("username")
    private String username;

    @Size(min = 4,max = 30)
    @NotBlank(message = "username is null")
    @TableField("nickname")
    private String nickname;


    @TableField("mobile")
    private String mobile;

    @Size(min = 4,max = 30)
    @NotBlank(message = "username is null")
    @TableField("password")
    private String password;

    @TableField("email")
    private String email;

    @TableField("gender")
    private boolean gender;

    @TableField(exist = false)
    private String gender$;

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

    @TableField(exist = false)
    private String createTime$;

    @TableField(exist = false)
    private String createBy$;

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
        if (createBy != null){
            UsersService bean = SpringUtils.getBean(UsersService.class);
            User user = bean.getById(createBy);
            if (user != null) {
                createBy$ = user.getUsername();
            }
        }
    }

    public void setId(Long id) {
        if (id != null){
            this.key = String.valueOf(id);
            this.id = id;
        }

    }

    public void setLastLogin(Date lastLogin) {
        if (lastLogin != null){
            this.lastLogin = lastLogin;
            this.lastLogin$ = DateFormat.dmy(lastLogin);
        }
    }

    public void setCreateTime(Date createTime) {
        if (createTime != null){
            this.createTime = createTime;
            this.createTime$ = DateFormat.dmy(createTime);
        }
    }

    public void setRoleId(Long roleId){
        if (roleId != null){
            this.roleId = roleId;
            RoleService bean = SpringUtils.getBean(RoleService.class);
            Role role = bean.getById(roleId);
            this.role = role.getName();
        }

    }

    public void setGender(boolean gender){
        this.gender = gender;
        if (gender) {
            gender$ = "Male";
        }else {
            gender$ = "Female";
        }
    }
}
