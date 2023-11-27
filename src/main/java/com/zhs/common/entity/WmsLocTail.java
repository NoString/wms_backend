package com.zhs.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;


import com.zhs.system.utils.DateFormat;
import lombok.Data;
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
@Data
@TableName("wms_loc_tail")
public class WmsLocTail implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("loc_no")
    private String locNo;

    @TableField("m_name")
    private String name;

    @TableField("m_code")
    private String code;

    @TableField("m_classify_name")
    private String classifyName;



    @TableField("m_weight")
    private Double mWeight;

    @TableField("m_color")
    private String mColor;

    @TableField("m_unit")
    private String mUnit;

    @TableField("m_memo")
    private String mMemo;

    @TableField("create_time")
    private Date createTime;

    @TableField("create_by")
    private String createBy;

    @TableField("update_time")
    private Date updateTime;

    @TableField("update_by")
    private String updateBy;

    @TableField("m_id")
    private Long materialId;

    private Float qty;

    @TableField(exist = false)
    private String key;

    @TableField(exist = false)
    private String createTime$;

    @TableField(exist = false)
    private String expiredDate$;

    @TableField(exist = false)
    private Integer progress;
    @TableField("m_expired_date")
    private Date expiredDate;




    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
        this.createTime$ = DateFormat.dmy(createTime);
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
        this.expiredDate$ = DateFormat.dmy(expiredDate);

        if (createTime != null && expiredDate != null){
            //设置进度条
            LocalDateTime startTime = LocalDateTime.ofInstant(createTime.toInstant(), ZoneId.systemDefault());
            LocalDateTime endTime = LocalDateTime.ofInstant(expiredDate.toInstant(), ZoneId.systemDefault());
            LocalDateTime currentTime = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());

            long totalDuration = ChronoUnit.SECONDS.between(startTime, endTime);
            long remainingDuration = ChronoUnit.SECONDS.between(currentTime, endTime);
            if (totalDuration == 0) {
                throw new IllegalArgumentException("开始时间和结束时间不能相同");
            }

            // 防止当前时间超出时间范围
            if (remainingDuration < 0) {
                setProgress(0);
            }
            setProgress((int) ((remainingDuration * 100) / totalDuration));
        }


    }

    public void setId(Long id) {
        if (id != null){
            this.key = String.valueOf(id);
            this.id = id;
        }
    }
}
