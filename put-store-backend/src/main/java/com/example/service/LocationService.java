package com.example.service;

import com.example.entity.Location;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface LocationService extends IService<Location> {

    Location add(String zhangwei, String xzuibiao, String yzuobiao, String detailPosition, Integer voyageId, Integer seaAreaId);
}
