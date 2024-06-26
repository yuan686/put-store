package com.example.service;

import com.example.common.PageFormVo;
import com.example.common.Result;
import com.example.entity.Store;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.vo.StoreSearchParams;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 */
public interface StoreService extends IService<Store> {

    Result upload(MultipartFile file);

    Result getList(PageFormVo pageFormVo, StoreSearchParams storeSearchParams);
}
