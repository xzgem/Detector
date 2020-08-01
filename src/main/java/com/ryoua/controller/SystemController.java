package com.ryoua.controller;

import com.github.pagehelper.PageInfo;
import com.ryoua.model.SystemInfo;
import com.ryoua.model.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * * @Author: RyouA
 * * @Date: 2020/7/18
 **/
@RestController
@Slf4j
public class SystemController extends BaseController{


    @PostMapping(value = "/machine/register")
    public Result getSystemInfoFromServer(@RequestBody SystemInfo systemInfo) {
        Integer autoRegister = systemInfo.getAutoRegister();
        log.info(systemInfo.toString());
        SystemInfo machineinfoByIp = systemInfoService.getMachineinfoByIp(systemInfo.getIp());
        redisUtil.set(DETECTOR_SYSTEMINFO + systemInfo.getId(), gson.toJson(systemInfo));

        if (autoRegister == 1 && machineinfoByIp == null) {
//            boolean flag = machineInfoService.addMachine(machineInfo);
//            if (flag)
//                return Result.SUCCESS();
//            else
//                return Result.FAIL();
        }

        return Result.SUCCESS();
    }

    @GetMapping("/machineInfo")
    public Result getAllMachineInfoByUserName(@RequestParam(value = "page") int page, @RequestParam(value = "limit") int limit) {
        PageInfo<SystemInfo> list = systemInfoService.getAllMachineInfoByUserName("123", page, limit);
        return new Result(0, "查询成功", list.getList(), list.getTotal());
    }

    @GetMapping("/machineInfo/{id}")
    public Result getMachineInfoById(@PathVariable("id") Integer id) {
        SystemInfo systemInfo = systemInfoService.getMachineInfoById(id);
        return new Result(0, "ok", systemInfo);
    }

    @GetMapping("/machineInfo/{ip}/{mac}")
    public Result searchMachineInfoByIpAndMac(@PathVariable(value = "ip", required = false) String ip,
                                              @PathVariable(value = "mac", required = false) String mac,
                                              @RequestParam(value = "page") int page,
                                              @RequestParam(value = "limit") int limit) {
        PageInfo<SystemInfo> list = systemInfoService.searchMachineInfoByIpAndMac(ip, mac, page, limit);
        return new Result(0, "ok", list.getList(), list.getTotal());
    }

    @PatchMapping("/machineInfo/{id}")
    public Result updateMachineInfoRemark(@PathVariable("id") Integer id, @RequestBody Map<String,Object> remark) {
        boolean flag = systemInfoService.updateMachineInfoRemark(id, (String) remark.get("remark"));
        if (flag)
            return Result.SUCCESS();
        else
            return Result.FAIL();
    }

    @PostMapping("/machineInfo")
    public Result addMachineInfo(@RequestBody SystemInfo systemInfo) {
        return Result.SUCCESS();
    }

    @DeleteMapping("/machineInfo/{id}")
    public Result deleteMachineById(@PathVariable("id") Integer id) {
        systemInfoService.deleteMachineById(id);
        return Result.SUCCESS();
    }

    @DeleteMapping("/machineInfo")
    public Result deleteMachineByIds(@RequestBody Map<String, Object> delete) {
        systemInfoService.deleteMachineByIds((List) delete.get("delete"));
        return Result.SUCCESS();
    }
}
