package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 航次表
 * @TableName voyage
 */
@TableName(value ="voyage")
@Data
public class Voyage implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer voyageId;

    /**
     * 
     */
    private Integer shipId;

    /**
     * 
     */
    private String voyageName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}