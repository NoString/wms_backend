package com.zhs.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("sys_operate_log")
@Data
public class OperateLog {
    private Long id;
    private String action;
    private Long userId;
    private String ip;
    private String request;
    private String response;
    private Date createTime;
}
