package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.PageFormVo;
import com.example.common.Result;
import com.example.entity.*;
import com.example.entity.vo.StoreSearchParams;
import com.example.entity.vo.StoreVo;
import com.example.service.*;
import com.example.mapper.StoreMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 *
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store>
    implements StoreService{
    private final ShipTypeService shipTypeService;
    private final VoyageService voyageService;
    private final SeaAreaService seaAreaService;
    private final LocationService locationService;
    private final RedisTemplate<String,Object> redisTemplate;

    @Override
    public Result upload(MultipartFile file) {
        //判断文件类型,获取workbook
        Workbook workbook = getWorkBook(file);
        Sheet sheet = workbook.getSheetAt(0);
        //生成唯一ID，作为redis缓存的key
        String uuid = UUID.randomUUID().toString();
        for (int i = 3; i <= sheet.getPhysicalNumberOfRows(); i++) {
            //获取每一行
            Row row = sheet.getRow(i);
            if(row == null
                    || row.getCell(0) == null
                    || row.getCell(0).getCellType() == CellType.BLANK) {
                continue;
            }
            //保存每一行数据,TODO:若想实现局部成功局部失败，则事务加在详细的方法体中，
            // 否则加在该方法上即可,若想导出失败数据，需要将错误文件另存到本地，再使用redis缓存记录
            try {
                saveData(row, uuid);
            } catch (Exception e) {
                //TODO：记录多少条记录失败，并响应给前端，让前端确认是否继续上传\
                log.error("第{}行数据有误",i);
            } finally {
                redisTemplate.delete(uuid+"*");
            }
        }
        return Result.success( "上传成功");
    }

    @Override
    public Result getList(PageFormVo pageFormVo, StoreSearchParams storeSearchParams) {
        int pageNum = pageFormVo.getPageNum();
        int pageSize = pageFormVo.getPageSize();
        PageHelper.startPage(pageNum,pageSize);
        List<StoreVo> storeVoList = baseMapper.getList(storeSearchParams);
        PageInfo<StoreVo> infoList = new PageInfo<>(storeVoList);
        return Result.success("查询成功", infoList);
    }

    //保证一行出问题，不会影响整体（无需满足一起成功前提使用）
    @Transactional
    public void saveData(Row row, String uuid) {

        String yangpingNum = convertType(row.getCell(1));
        String diaochaShip = convertType(row.getCell(2));
        String hangci = convertType(row.getCell(3));
        String haiyu = convertType(row.getCell(4));
        String zhangwei = convertType(row.getCell(5));
        String xzuibiao = convertType(row.getCell(6));
        String yzuobiao = convertType(row.getCell(7));
        String detailPosition = convertType(row.getCell(8));
        String endDept = convertType(row.getCell(9));
        String codeLength = convertType(row.getCell(10));
        String savePosition = convertType(row.getCell(11));
        String saveStatus = convertType(row.getCell(12));
        String remark = convertType(row.getCell(13));
        //判断调查船是否存在记录
        ShipType shipType = shipTypeService.addIfPresent(diaochaShip, uuid);
        //判断航次是否存在记录
        Voyage voyage = voyageService.addIfPresent(shipType.getShipId(), hangci,uuid);
        //保存海域
        SeaArea seaArea = seaAreaService.addIfPresent(haiyu,uuid);
        //保存详细位置
        Location location = locationService.add(zhangwei, xzuibiao, yzuobiao, detailPosition, voyage.getVoyageId(),seaArea.getSeaAreaId());
        //保存入单表
        Store store = new Store();
        store.setSampleNum(yangpingNum);
        store.setLocationId(location.getLocationId());
        store.setEndDept(new BigDecimal(endDept));
        store.setHeartLength(new BigDecimal(codeLength));
        store.setStorePosition(savePosition);
        store.setSaveStatus(new BigDecimal(StringUtils.isEmpty(saveStatus) ?"0":saveStatus));
        store.setRemark(remark);
        save(store);
    }

    /**
     * 将cell的值转为字符串
     */
    public String convertType(Cell cell) {
        CellType cellType = cell.getCellType();
        String value = "";
        switch (cellType) {
            case STRING:
                value = cell.getStringCellValue();
                break;
            case NUMERIC:
                value = String.valueOf(cell.getNumericCellValue());
                break;
            case BOOLEAN:
                value = String.valueOf(cell.getBooleanCellValue());
                break;
            case BLANK:
                value = String.valueOf(cell.getStringCellValue());
                break;
            default:
                value = String.valueOf(cell.getStringCellValue());
                break;
        }
        return value;
    }

    private Workbook getWorkBook(MultipartFile file) {
        //判断文件是否为xls或xlsx
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            throw new RuntimeException("系统无法识别文件名");
        }
        try {
            InputStream inputStream = file.getInputStream();
            if (originalFilename.endsWith(".xls")) {
                return new HSSFWorkbook(inputStream);
            } else if (originalFilename.endsWith(".xlsx")) {
                return new XSSFWorkbook(inputStream);
            }
            throw new RuntimeException("文件格式错误");
        } catch (IOException e) {
            throw new RuntimeException("文件读取失败");
        }
    }
}




