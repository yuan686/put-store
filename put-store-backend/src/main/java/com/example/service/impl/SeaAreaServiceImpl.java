package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.SeaArea;
import com.example.entity.Voyage;
import com.example.service.SeaAreaService;
import com.example.mapper.SeaAreaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 *
 */
@Service
@RequiredArgsConstructor
public class SeaAreaServiceImpl extends ServiceImpl<SeaAreaMapper, SeaArea>
    implements SeaAreaService{
    private final RedisTemplate<String,Object> redisTemplate;

    @Override
    public SeaArea addIfPresent(String haiyu, String uuid) {
        String key1 = uuid+haiyu;
        String key2 = haiyu;
        Object value = redisTemplate.opsForHash().get(key1, key2);
        if(Objects.nonNull(value) && value instanceof SeaArea) {
            return (SeaArea)value;
        }
        SeaArea seaArea = new SeaArea();
        seaArea.setSeaAreaName(haiyu);
        save(seaArea);
        redisTemplate.opsForHash().put(key1, key2, seaArea);
        return seaArea;
    }
}




