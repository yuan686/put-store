package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.ShipType;
import com.example.service.ShipTypeService;
import com.example.mapper.ShipTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 *
 */
@Service
@RequiredArgsConstructor
public class ShipTypeServiceImpl extends ServiceImpl<ShipTypeMapper, ShipType>
    implements ShipTypeService{
    private final RedisTemplate<String,Object> redisTemplate;

    @Override
    public ShipType addIfPresent(String diaochaShip, String uuid) {
        String key1 = uuid+diaochaShip;
        String key2 = diaochaShip;
        Object value = redisTemplate.opsForHash().get(key1, key2);
        if(Objects.nonNull(value) && value instanceof ShipType) {
            return (ShipType)value;
        }
        ShipType shipType = new ShipType();
        shipType.setShipName(diaochaShip);
        save(shipType);
        redisTemplate.opsForHash().put(key1, key2, shipType);
        return shipType;
    }
}




