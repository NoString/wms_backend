package com.zhs.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author ZHU HANGSHUAI
 * @since 2023-11-22
 */
@Getter
@Setter
@TableName("wms_loc_sts")
public class WmsLocSts implements Serializable {

    private static final long serialVersionUID = 1L;



    @TableId("id")
    private Long id;

    @TableField("loc_sts")
    private String locSts;

    @TableField("loc_desc")
    private String locDesc;

    @TableField(exist = false)
    private String key;

    public void setId(Long id) {
        if (id != null){
            this.key = String.valueOf(id);
            this.id = id;
        }
    }
}
