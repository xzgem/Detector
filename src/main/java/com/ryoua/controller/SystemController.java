package com.ryoua.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.ryoua.model.SystemInfo;
import com.ryoua.model.common.Result;
import com.ryoua.service.SystemInfoService;
import com.ryoua.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * * @Author: RyouA
 * * @Date: 2020/7/18
 **/
@RestController
@Slf4j
public class SystemController {

    @Autowired
    private Gson gson;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private SystemInfoService systemInfoService;

    private static final String DETECTOR_SYSTEMINFO = "detector:systeminfo";

    @PostMapping(value = "systemInfo")
    public Result getSystemInfoFromServer(@RequestBody SystemInfo systemInfo) {
        log.info(systemInfo.toString());
        redisUtil.set(DETECTOR_SYSTEMINFO + systemInfo.getMac(), gson.toJson(systemInfo));
        return Result.SUCCESS();
    }

    @GetMapping("/systemInfo/all")
    public Result getAllSystemInfoByUserName(@RequestParam(value = "page") int page, @RequestParam(value = "limit") int limit) {
        PageInfo<SystemInfo> list = systemInfoService.getAllSystemInfoByUserName("123", page, limit);
        return new Result(0, "ok", list.getList(), list.getTotal());
    }

    @PostMapping("/systemInfo/add")
    public Result addSystemInfo(@RequestBody SystemInfo systemInfo) {
        return Result.SUCCESS();
    }
}
