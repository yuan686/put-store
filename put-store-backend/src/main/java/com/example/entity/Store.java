package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 入库单
 * @TableName store
 */
@TableName(value ="store")
@Data
public class Store implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer sampleId;

    private String sampleNum;

    /**
     * 
     */
    private Integer locationId;

    /**
     * 
     */
    private BigDecimal endDept;

    /**
     * 
     */
    private BigDecimal heartLength;

    /**
     * 
     */
    private String storePosition;

    /**
     * 
     */
    private BigDecimal saveStatus;

    /**
     * 
     */
    private String remark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}