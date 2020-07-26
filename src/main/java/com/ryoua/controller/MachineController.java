package com.ryoua.controller;

import com.github.pagehelper.PageInfo;
import com.ryoua.model.MachineInfo;
import com.ryoua.model.common.Result;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * * @Author: RyouA
 * * @Date: 2020/7/18
 **/
@RestController
@Slf4j
public class MachineController extends BaseController{


    @PostMapping(value = "/machine/register")
    public Result getSystemInfoFromServer(@RequestBody MachineInfo machineInfo) {
        boolean autoRegister = machineInfo.getAutoRegister();
        log.info(machineInfo.toString());
        MachineInfo machineinfoByIp = machineInfoService.getMachineinfoByIp(machineInfo.getIp());
        redisUtil.set(DETECTOR_SYSTEMINFO + machineInfo.getMac(), gson.toJson(machineInfo));

        if (autoRegister && machineinfoByIp == null) {
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
        PageInfo<MachineInfo> list = machineInfoService.getAllMachineInfoByUserName("123", page, limit);
        return new Result(0, "ok", list.getList(), list.getTotal());
    }

    @GetMapping("/machineInfo/{id}")
    public Result getMachineInfoById(@PathVariable("id") Integer id) {
        MachineInfo machineInfo = machineInfoService.getMachineInfoById(id);
        return new Result(0, "ok", machineInfo);
    }

    @GetMapping("/machineInfo/{ip}/{mac}")
    public Result searchMachineInfoByIpAndMac(@PathVariable(value = "ip", required = false) String ip,
                                              @PathVariable(value = "mac", required = false) String mac,
                                              @RequestParam(value = "page") int page,
                                              @RequestParam(value = "limit") int limit) {
        PageInfo<MachineInfo> list = machineInfoService.searchMachineInfoByIpAndMac(ip, mac, page, limit);
        return new Result(0, "ok", list.getList(), list.getTotal());
    }

    @PatchMapping("/machineInfo/{id}")
    public Result updateMachineInfoRemark(@PathVariable("id") Integer id, @RequestBody Map<String,Object> remark) {
        boolean flag = machineInfoService.updateMachineInfoRemark(id, (String) remark.get("remark"));
        if (flag)
            return Result.SUCCESS();
        else
            return Result.FAIL();
    }

    @PostMapping("/machineInfo")
    public Result addMachineInfo(@RequestBody MachineInfo machineInfo) {
        return Result.SUCCESS();
    }

    @DeleteMapping("/machineInfo/{id}")
    public Result deleteMachineById(@PathVariable("id") Integer id) {
        machineInfoService.deleteMachineById(id);
        return Result.SUCCESS();
    }

    @DeleteMapping("/machineInfo")
    public Result deleteMachineByIds(@RequestBody Map<String, Object> delete) {
        machineInfoService.deleteMachineByIds((List) delete.get("delete"));
        return Result.SUCCESS();
    }
}
