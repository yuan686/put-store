package com.example.service;

import com.example.entity.SeaArea;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface SeaAreaService extends IService<SeaArea> {

    SeaArea addIfPresent(String haiyu, String uuid);
}
