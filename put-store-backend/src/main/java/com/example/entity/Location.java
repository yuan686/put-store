package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 详细位置表
 * @TableName location
 */
@TableName(value ="location")
@Data
public class Location implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer locationId;

    /**
     * 
     */
    private Integer voyageId;

    /**
     * 
     */
    private Integer seaAreaId;

    /**
     * 
     */
    private String position;

    /**
     * 
     */
    private BigDecimal xCoordinate;

    /**
     * 
     */
    private BigDecimal yCoordinate;

    /**
     * 
     */
    private String detailedAddress;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}