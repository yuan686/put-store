package com.example.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Yuan
 * @description
 * @date 2024/6/25
 */
@Data
public class StoreVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer sampleId;
    private String sampleNum;
    private String shipName;
    private String voyageName;
    private String seaAreaName;
    private String position;
    private BigDecimal xCoordinate;

    private BigDecimal yCoordinate;

    private String detailedAddress;

    private BigDecimal endDept;

    private BigDecimal heartLength;

    private String storePosition;


    private BigDecimal saveStatus;

    private String remark;
}
