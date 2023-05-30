package com.zhs.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("sys_user")
public class User {
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
    private boolean status;
}
