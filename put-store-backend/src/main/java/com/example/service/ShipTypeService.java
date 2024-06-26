package com.example.service;

import com.example.entity.ShipType;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface ShipTypeService extends IService<ShipType> {

    ShipType addIfPresent(String diaochaShip, String uuid);
}
