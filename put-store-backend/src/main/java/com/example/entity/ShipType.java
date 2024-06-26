package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 调查船表
 * @TableName ship_type
 */
@TableName(value ="ship_type")
@Data
public class ShipType implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer shipId;

    /**
     * 
     */
    private String shipName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}