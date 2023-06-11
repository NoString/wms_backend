package com.zhs.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@TableName("sys_user")
public class Users {

    @TableField(exist = false)
    private String key;
    private Long id;
    private int hostId;
    private int deptId;
    private int roleId;
    private String username;
    private String nickname;
    private String mobile;
    private String password;
    private String email;
    private boolean gender;
    private Date createTime;
    private Long createBy;
    private Date updateTime;
    private Long updateBy;
    private Date lastLogin;
    private boolean status;


    @TableField(exist = false)
    private String lastLogin$;

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
}
