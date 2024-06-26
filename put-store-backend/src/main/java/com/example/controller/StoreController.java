package com.example.controller;

import com.example.common.PageFormVo;
import com.example.common.Result;
import com.example.entity.vo.StoreSearchParams;
import com.example.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Yuan
 * @description
 * @date 2024/6/25
 */
@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;
    @PostMapping("/upload")
    public Result upload(MultipartFile file) {
        return storeService.upload(file);
    }

    @GetMapping("/list")
    public Result list(PageFormVo pageFormVo, StoreSearchParams storeSearchParams) {
        return storeService.getList(pageFormVo, storeSearchParams);
    }
}
