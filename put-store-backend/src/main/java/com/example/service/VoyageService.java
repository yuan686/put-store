package com.example.service;

import com.example.entity.Voyage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface VoyageService extends IService<Voyage> {

    Voyage addIfPresent(Integer shipId, String hangci, String uuid);
}
