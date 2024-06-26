package com.example.mapper;

import com.example.entity.Store;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.vo.StoreSearchParams;
import com.example.entity.vo.StoreVo;

import java.util.List;
import java.util.Map;

/**
 * @Entity com.example.entity.Store
 */
public interface StoreMapper extends BaseMapper<Store> {

    List<StoreVo> getList(StoreSearchParams storeSearchParams);
}




