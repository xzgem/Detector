package com.ryoua.controller;

import com.github.pagehelper.PageInfo;
import com.ryoua.handler.UserLocal;
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


    @PostMapping(value = "/systemInfo/register/{autoregister}")
    @ApiOperation(value = "注册机器信息", notes = "从客户端注册机器信息", httpMethod = "POST")
    public Result getSystemInfoFromServer(@RequestBody SystemInfo systemInfo, @PathVariable("autoregister") Integer autoregister) {
        Integer autoRegister = systemInfo.getAutoRegister();
        log.info(systemInfo.toString());
        SystemInfo machineinfoByIp = systemInfoService.findSystemInfoByIpAndUser(systemInfo.getIp(), UserLocal.getCurrentUserId());

        if (autoRegister == 1) {
            redisUtil.set(DETECTOR_SYSTEMINFO + systemInfo.getId(), gson.toJson(systemInfo));
            if (machineinfoByIp == null) {
                Integer flag = systemInfoService.insertSystemInfo(systemInfo);
                return resultByFlag(flag);
            }
        }

        return Result.SUCCESS();
    }

    @GetMapping("/systemInfo")
    @ApiOperation(value = "获取机器信息", notes = "获取全部机器信息", httpMethod = "GET")
    public Result findAllSystemInfoByUser(@RequestParam(value = "page") int page, @RequestParam(value = "limit") int limit) {
        PageInfo<SystemInfo> list = systemInfoService.findAllSystemInfoByUser(UserLocal.getCurrentUserId(), page, limit);
        return new Result(0, "查询成功", list.getList(), list.getTotal());
    }

    @GetMapping("/systemInfo/{id}")
    @ApiOperation(value = "查找机器信息", notes = "根据ID查找机器信息", httpMethod = "GET")
    public Result findSystemInfoById(@PathVariable("id") Integer id) {
        SystemInfo systemInfo = systemInfoService.findSystemInfoById(id);
        return new Result(0, "ok", systemInfo);
    }

    @GetMapping("/systemInfo/ip")
    @ApiOperation(value = "查找机器信息", notes = "根据IP模糊查找机器信息", httpMethod = "GET")
    public Result findSystemInfoByIp(@RequestParam(value = "ip") String ip,
                                              @RequestParam(value = "page") int page,
                                              @RequestParam(value = "limit") int limit) {
        PageInfo<SystemInfo> list = systemInfoService.findSystemInfoIpLikeAndUser(ip, UserLocal.getCurrentUserId(), page, limit);
        return new Result(0, "ok", list.getList(), list.getTotal());
    }

    @PutMapping("/systemInfo/{id}/remark")
    @ApiOperation(value = "更新机器信息", notes = "更新机器信息备注", httpMethod = "PUT")
    public Result updateSystemInfoRemark(@PathVariable("id") Integer id, @RequestBody Map<String,Object> remark) {
        Integer flag = systemInfoService.updateSystemInfoRemarkById(id, (String) remark.get("remark"));
        return  resultByFlag(flag);
    }

    @PostMapping("/systemInfo")
    @ApiOperation(value = "新增机器信息", notes = "新增系统信息", tags = "machineInfo", httpMethod = "POST")
    public Result insertSystemInfo(@RequestBody SystemInfo systemInfo) {
        return Result.SUCCESS();
    }

    @DeleteMapping("/systemInfo/{id}")
    @ApiOperation(value = "删除机器信息", notes = "根据Id删除机器信息", tags = "machineInfo", httpMethod = "DELETE")
    public Result deleteSystemInfoById(@PathVariable("id") Integer id) {
        Integer flag = systemInfoService.deleteSystemInfoById(id);
        return  resultByFlag(flag);
    }

//    @DeleteMapping("/machineInfo")
//    @ApiOperation(value = "删除机器信息", notes = "根据Id数组删除机器信息", tags = "machineInfo", httpMethod = "DELETE")
//    public Result deleteMachineByIds(@RequestBody Map<String, Object> delete) {
//        systemInfoService.deleteMachineByIds((List) delete.get("delete"));
//        return Result.SUCCESS();
//    }
}
