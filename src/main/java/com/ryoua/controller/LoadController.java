package com.ryoua.controller;

import com.ryoua.model.LoadInfo;
import com.ryoua.model.common.Result;
import com.ryoua.model.echarts.EchartsData;
import com.ryoua.model.echarts.EchartsResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/26
 **/
@RestController
@Slf4j
public class LoadController extends BaseController{
    @PostMapping(value = "/loadInfo/register")
    public Result getLoadInfoFromServer( @RequestBody LoadInfo loadInfo) {
        log.info(loadInfo.toString());
        redisUtil.zAdd(DETECTOR_LOADINFO + loadInfo.getMid(), gson.toJson(loadInfo), loadInfo.getUpdateTimeMills());
        redisUtil.expire(DETECTOR_LOADINFO + loadInfo.getMid(), 1, TimeUnit.DAYS);
        return Result.SUCCESS();
    }

    @GetMapping(value = "/loadInfo/{oid}/min")
    public Result getLoadInfo(@PathVariable("oid") String oid) {
        Long length = redisUtil.zZCard(DETECTOR_LOADINFO + oid);
        Set<String> strings = redisUtil.zRange(DETECTOR_LOADINFO + oid, length - 12, length);

        List<Double> memoryAllList = new ArrayList<>();
        List<Double> memorySwapList = new ArrayList<>();
        List<String> stringlist = new ArrayList<>();

        for (String str : strings) {
            LoadInfo loadInfo = gson.fromJson(str, LoadInfo.class);
            memoryAllList.add(loadInfo.getMemoryUse() / loadInfo.getMemoryAll());
            memorySwapList.add(loadInfo.getMemorySwapUse() / loadInfo.getMemorySwapAll());
            stringlist.add(gson.fromJson(str, LoadInfo.class).getUpdateTime().split(" ")[1]);
        }
        EchartsResult echartsResult = new EchartsResult();
        EchartsData[] echartsDatas = new EchartsData[2];

        EchartsData memoryAll = new EchartsData();
        EchartsData memorySwap = new EchartsData();

        memoryAll.setName("总内存使用率");
        memoryAll.setType("line");
        memoryAll.setStack("百分比1");
        memoryAll.setAreaStyle(new HashMap());
        memoryAll.setData(memoryAllList.toArray());

        memorySwap.setName("交换内存使用率");
        memorySwap.setType("line");
        memorySwap.setStack("百分比2");
        memorySwap.setAreaStyle(new HashMap());
        memorySwap.setData(memorySwapList.toArray());

        echartsDatas[0] = memoryAll;
        echartsDatas[1] = memorySwap;
        echartsResult.setEchartsData(echartsDatas);
        echartsResult.setXray(stringlist.toArray());
        echartsResult.setYray("value");
        return Result.SUCCESS(echartsResult);
    }

}
