package com.zhs.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("sys_menu")
@Data
public class Menu {
    private Long id;
    private String menuName;
    private Long fatherNode;
}
