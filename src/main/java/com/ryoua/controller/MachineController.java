package com.ryoua.controller;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.ryoua.config.JWTIgnore;
import com.ryoua.model.MachineInfo;
import com.ryoua.model.common.Result;
import com.ryoua.service.MachineInfoService;
import com.ryoua.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * * @Author: RyouA
 * * @Date: 2020/7/18
 **/
@RestController
@Slf4j
public class MachineController {

    @Autowired
    private Gson gson;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private MachineInfoService machineInfoService;

    private static final String DETECTOR_SYSTEMINFO = "detector:machineinfo";

    @PostMapping(value = "/machine/register")
    public Result getSystemInfoFromServer(@RequestBody MachineInfo machineInfo) {
        log.info(machineInfo.toString());
        redisUtil.set(DETECTOR_SYSTEMINFO + machineInfo.getMac(), gson.toJson(machineInfo));
        MachineInfo machine = machineInfoService.getMachineInfoByIpAndUserId(machineInfo.getIp(), 1);
        return Result.SUCCESS();
    }

    @GetMapping("/machineInfo")
    public Result getAllMachineInfoByUserName(@RequestParam(value = "page") int page, @RequestParam(value = "limit") int limit) {
        PageInfo<MachineInfo> list = machineInfoService.getAllMachineInfoByUserName("123", page, limit);
        return new Result(0, "ok", list.getList(), list.getTotal());
    }

    @GetMapping("/machineInfo/{id}")
    public Result getMachineInfoById(@PathVariable("id") Integer id) {
        MachineInfo machineInfo = machineInfoService.getMachineInfoById(id);
        return new Result(0, "ok", machineInfo);
    }

    @PostMapping("/machineInfo")
    public Result addMachineInfo(@RequestBody MachineInfo machineInfo) {
        return Result.SUCCESS();
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteMachineById(@PathVariable("id") Integer id) {
        machineInfoService.deleteMachineById(id);
        return Result.SUCCESS();
    }
}
