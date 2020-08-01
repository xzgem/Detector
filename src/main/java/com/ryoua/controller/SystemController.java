package com.ryoua.controller;

import com.github.pagehelper.PageInfo;
import com.ryoua.model.SystemInfo;
import com.ryoua.model.common.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "注册机器信息", notes = "从客户端注册机器信息", tags = "machineInfo", httpMethod = "POST")
    @ApiImplicitParams({
    })
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
    @ApiOperation(value = "获取机器信息", notes = "获取全部机器信息", tags = "machineInfo", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "一页数量", required = true, dataType = "Integer"),
    })
    public Result findAllMachineInfoByUserName(@RequestParam(value = "page") int page, @RequestParam(value = "limit") int limit) {
        PageInfo<SystemInfo> list = systemInfoService.findAllMachineInfoByUserName("123", page, limit);
        return new Result(0, "查询成功", list.getList(), list.getTotal());
    }

    @GetMapping("/machineInfo/{id}")
    @ApiOperation(value = "查找机器信息", notes = "根据ID查找机器信息", tags = "machineInfo", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "机器的id", required = true, dataType = "Integer"),
    })
    public Result findMachineInfoById(@PathVariable("id") Integer id) {
        SystemInfo systemInfo = systemInfoService.findMachineInfoById(id);
        return new Result(0, "ok", systemInfo);
    }

    @GetMapping("/machineInfo/ip/{ip}")
    @ApiOperation(value = "查找机器信息", notes = "根据IP查找机器信息", tags = "machineInfo", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ip", value = "机器的IP", required = true, dataType = "String"),
            @ApiImplicitParam(name = "page", value = "页数", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "一页数量", required = true, dataType = "Integer"),
    })
    public Result findMachineInfoByIp(@PathVariable(value = "ip", required = false) String ip,
                                              @RequestParam(value = "page") int page,
                                              @RequestParam(value = "limit") int limit) {
        PageInfo<SystemInfo> list = systemInfoService.findMachineInfoByIp(ip, page, limit);
        return new Result(0, "ok", list.getList(), list.getTotal());
    }

    @PutMapping("/machineInfo/{id}")
    @ApiOperation(value = "新增机器信息", notes = "根据Id删除机器信息", tags = "machineInfo", httpMethod = "PUT")
    @ApiImplicitParams({
    })
    public Result updateMachineInfoRemark(@PathVariable("id") Integer id, @RequestBody Map<String,Object> remark) {
        boolean flag = systemInfoService.updateMachineInfoRemark(id, (String) remark.get("remark"));
        if (flag)
            return Result.SUCCESS();
        else
            return Result.FAIL();
    }

    @PostMapping("/machineInfo")
    @ApiOperation(value = "新增机器信息", notes = "根据Id删除机器信息", tags = "machineInfo", httpMethod = "POST")
    @ApiImplicitParams({
    })
    public Result insertMachineInfo(@RequestBody SystemInfo systemInfo) {
        return Result.SUCCESS();
    }

    @DeleteMapping("/machineInfo/{id}")
    @ApiOperation(value = "删除机器信息", notes = "根据Id删除机器信息", tags = "machineInfo", httpMethod = "DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "机器的id", required = true, dataType = "Integer"),
    })
    public Result deleteMachineById(@PathVariable("id") Integer id) {
        systemInfoService.deleteMachineById(id);
        return Result.SUCCESS();
    }

    @DeleteMapping("/machineInfo")
    @ApiOperation(value = "删除机器信息", notes = "根据Id数组删除机器信息", tags = "machineInfo", httpMethod = "DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "delete", value = "Map的key", required = true, dataType = "String"),
            @ApiImplicitParam(name = "num", value = "Map的value", required = true, dataType = "Integer"),
    })
    public Result deleteMachineByIds(@RequestBody Map<String, Object> delete) {
        systemInfoService.deleteMachineByIds((List) delete.get("delete"));
        return Result.SUCCESS();
    }
}
