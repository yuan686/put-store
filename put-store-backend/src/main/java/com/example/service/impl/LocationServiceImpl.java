package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Location;
import com.example.entity.Voyage;
import com.example.service.LocationService;
import com.example.mapper.LocationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 */
@Service
@RequiredArgsConstructor
public class LocationServiceImpl extends ServiceImpl<LocationMapper, Location>
    implements LocationService{

    @Override
    public Location add(String zhangwei, String xzuibiao, String yzuobiao, String detailPosition, Integer voyageId, Integer seaAreaId) {
        Location location = new Location();
        location.setSeaAreaId(seaAreaId);
        location.setVoyageId(voyageId);
        location.setPosition(zhangwei);
        location.setXCoordinate(new BigDecimal(xzuibiao));
        location.setYCoordinate(new BigDecimal(yzuobiao));
        location.setDetailedAddress(detailPosition);
        save(location);
        return location;
    }
}




