package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 海域表
 * @TableName sea_area
 */
@TableName(value ="sea_area")
@Data
public class SeaArea implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer seaAreaId;

    /**
     * 
     */
    private String seaAreaName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}