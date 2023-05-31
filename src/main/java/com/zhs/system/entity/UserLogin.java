package com.zhs.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("sys_user_login")
@Data
public class UserLogin {
    private Long id;
    private Long userId;
    private String token;
    private Date createTime;
}
