package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Voyage;
import com.example.service.VoyageService;
import com.example.mapper.VoyageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 *
 */
@Service
@RequiredArgsConstructor
public class VoyageServiceImpl extends ServiceImpl<VoyageMapper, Voyage>
    implements VoyageService{
    private final RedisTemplate<String,Object> redisTemplate;
    @Override
    public Voyage addIfPresent(Integer shipId, String hangci, String uuid) {
        String key1 = uuid+hangci;
        String key2 = hangci;
        Object value = redisTemplate.opsForHash().get(key1, key2);
        if(Objects.nonNull(value) && value instanceof Voyage) {
            return (Voyage)value;
        }
        Voyage voyage = new Voyage();
        voyage.setShipId(shipId);
        voyage.setVoyageName(hangci);
        save(voyage);
        redisTemplate.opsForHash().put(key1, key2, voyage);
        return voyage;
    }
}




