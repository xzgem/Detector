package com.ryoua.controller;

import com.ryoua.model.Load;
import com.ryoua.model.common.Result;
import com.ryoua.model.echarts.EchartsData;
import com.ryoua.model.echarts.EchartsResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/26
 **/
@RestController
@Slf4j
public class LoadController extends BaseController{
    @PostMapping(value = "/load/register/{autoRegister}")
    public Result getLoadInfoFromServer(@RequestBody Load load, @PathVariable("autoRegister") Integer autoRegister) {
        if (autoRegister == 0) {
            log.info(load.toString());
        } else {
            redisUtil.zAdd(DETECTOR_LOADINFO + load.getCpuLoad().getMid(), gson.toJson(load), load.getCreateTimeMills());
            redisUtil.expire(DETECTOR_LOADINFO + load.getCpuLoad().getMid(), 1, TimeUnit.DAYS);
        }
        return Result.SUCCESS();
    }
//
//    @GetMapping(value = "/loadInfo/{oid}/min")
//    public Result getLoadInfo(@PathVariable("oid") String oid) {
//        Long length = redisUtil.zZCard(DETECTOR_LOADINFO + oid);
//        Set<String> strings = redisUtil.zRange(DETECTOR_LOADINFO + oid, length - 12, length);
//
//        List<Double> memoryAllList = new ArrayList<>();
//        List<Double> memorySwapList = new ArrayList<>();
//        List<String> stringlist = new ArrayList<>();
//
//        for (String str : strings) {
//            LoadInfo loadInfo = gson.fromJson(str, LoadInfo.class);
//            memoryAllList.add(loadInfo.getMemoryUse() / loadInfo.getMemoryAll());
//            memorySwapList.add(loadInfo.getMemorySwapUse() / loadInfo.getMemorySwapAll());
//            stringlist.add(gson.fromJson(str, LoadInfo.class).getUpdateTime().split(" ")[1]);
//        }
//        EchartsResult echartsResult = new EchartsResult();
//        EchartsData[] echartsDatas = new EchartsData[2];
//
//        EchartsData memoryAll = new EchartsData();
//        EchartsData memorySwap = new EchartsData();
//
//        memoryAll.setName("总内存使用率");
//        memoryAll.setType("line");
//        memoryAll.setStack("百分比1");
//        memoryAll.setAreaStyle(new HashMap());
//        memoryAll.setData(memoryAllList.toArray());
//
//        memorySwap.setName("交换内存使用率");
//        memorySwap.setType("line");
//        memorySwap.setStack("百分比2");
//        memorySwap.setAreaStyle(new HashMap());
//        memorySwap.setData(memorySwapList.toArray());
//
//        echartsDatas[0] = memoryAll;
//        echartsDatas[1] = memorySwap;
//        echartsResult.setEchartsData(echartsDatas);
//        echartsResult.setXray(stringlist.toArray());
//        echartsResult.setYray("value");
//        return Result.SUCCESS(echartsResult);
//    }
//
//    @GetMapping(value = "/loadInfo/{oid}/memory")
//    public Result findMemoryLoadInfo(@PathVariable("oid") String oid) {
//        Long length = redisUtil.zZCard(DETECTOR_LOADINFO + oid);
//        Set<String> strings = redisUtil.zRange(DETECTOR_LOADINFO + oid, length - 12, length);
//
//        List<Double> memoryAllList = new ArrayList<>();
//        List<Double> memorySwapList = new ArrayList<>();
//        List<String> stringlist = new ArrayList<>();
//
//        for (String str : strings) {
//            LoadInfo loadInfo = gson.fromJson(str, LoadInfo.class);
//            memoryAllList.add((loadInfo.getMemoryUse() / loadInfo.getMemoryAll()) * 100);
//            if (loadInfo.getMemorySwapUse() == 0)
//                memorySwapList.add(0.0);
//            else
//                memorySwapList.add((loadInfo.getMemorySwapUse() / loadInfo.getMemorySwapAll()) * 100);
//            stringlist.add(gson.fromJson(str, LoadInfo.class).getUpdateTime().split(" ")[1]);
//        }
//        EchartsResult echartsResult = new EchartsResult();
//        EchartsData[] echartsDatas = new EchartsData[2];
//
//        EchartsData memoryAll = new EchartsData();
//        EchartsData memorySwap = new EchartsData();
//
//        memoryAll.setName("总内存使用率");
//        memoryAll.setType("line");
//        memoryAll.setStack("百分比1");
//        memoryAll.setAreaStyle(new HashMap());
//        memoryAll.setData(memoryAllList.toArray());
//
//        memorySwap.setName("交换内存使用率");
//        memorySwap.setType("line");
//        memorySwap.setStack("百分比1");
//        memorySwap.setAreaStyle(new HashMap());
//        memorySwap.setData(memorySwapList.toArray());
//
//        echartsDatas[0] = memoryAll;
//        echartsDatas[1] = memorySwap;
//
//        echartsResult.setEchartsData(echartsDatas);
//        echartsResult.setXray(stringlist.toArray());
//        echartsResult.setYray("value");
//        return Result.SUCCESS(echartsResult);
//    }

}
