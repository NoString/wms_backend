package com.zhs.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@TableName("wms_loc_head")
public class WmsLocHead implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("loc_no")
    private String locNo;

    @TableField("loc_row")
    private String locRow;

    @TableField("loc_bay")
    private String locBay;

    @TableField("loc_level")
    private String locLevel;

    @TableField("loc_sts")
    private String locSts;

    @TableField("loc_type")
    private String locType;


    @TableField(exist = false)
    private String key;

    public void setId(Long id) {
        if (id != null){
            this.key = String.valueOf(id);
            this.id = id;
        }
    }

    public WmsLocHead(String locNo, String locRow, String locBay, String locLevel, String locSts, String locType) {
        this.locNo = locNo;
        this.locRow = locRow;
        this.locBay = locBay;
        this.locLevel = locLevel;
        this.locSts = locSts;
        this.locType = locType;
    }
}
