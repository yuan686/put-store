package com.example.common;

import lombok.Data;

import java.util.Map;

/**
 * @author Yuan
 * @description
 * @date 2024/6/25
 */
@Data
public class PageFormVo {
    private int pageNum;
    private int pageSize;
    private Map<String, Object> params;
}
