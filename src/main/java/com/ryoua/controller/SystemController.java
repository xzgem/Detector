package com.ryoua.controller;

import com.google.gson.Gson;
import com.ryoua.model.SystemInfo;
import com.ryoua.model.common.Result;
import com.ryoua.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    private static final String DETECTOR_SYSTEMINFO = "detector:systeminfo";

    @PostMapping(value = "systemInfo")
    public Result getSystemInfoFromServer(@RequestBody SystemInfo systemInfo) {
        log.info(systemInfo.toString());
        redisUtil.set(DETECTOR_SYSTEMINFO + systemInfo.getMac(), gson.toJson(systemInfo));
        return Result.SUCCESS();
    }

    @GetMapping("/systemInfo/all")
    public Result getAllSystemInfoByUserName() {

        return Result.SUCCESS();
    }
}
