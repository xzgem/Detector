package com.ryoua.controller;

import com.github.pagehelper.PageInfo;
import com.ryoua.config.TimeConsume;
import com.ryoua.handler.UserLocal;
import com.ryoua.model.CpuLoad;
import com.ryoua.model.Load;
import com.ryoua.model.MemoryLoad;
import com.ryoua.model.SystemInfo;
import com.ryoua.model.common.Result;
import com.ryoua.utils.TimeUtil;
import com.ryoua.vo.CpuVo;
import com.ryoua.vo.MemoryVo;
import com.ryoua.vo.TimeStampData;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;


/**
 * * @Author: RyouA
 * * @Date: 2020/7/18
 **/
@RestController
@Slf4j
@TimeConsume
public class SystemController extends BaseController{

//    @PostMapping(value = "/systemInfo/{mid}}/{autoregister}")
//    @ApiOperation(value = "设置某机器为自动注册", notes = "从客户端注册机器信息", httpMethod = "POST")
//    public Result sendSystemInfoAutoRegister(@RequestBody SystemInfo systemInfo,
//                                             @PathVariable("autoregister") Integer autoregister) {
//        HttpHeaders headers = new HttpHeaders();
//        HttpEntity<SystemInfo> request = new HttpEntity<>(headers);
//        String result = this.restTemplate.postForObject("http://" + systemInfo.getIp() + ":"  + "/systemInfo/register/" + autoRegister, request, String.class);
//        log.info(result);
//
//        return Result.SUCCESS();
//    }

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
        return new Result<>(0, "查询成功", list.getList(), list.getTotal());
    }

    @GetMapping("/systemInfo/{id}")
    @ApiOperation(value = "查找机器信息", notes = "根据ID查找机器信息", httpMethod = "GET")
    public Result findSystemInfoById(@PathVariable("id") Integer id) {
        SystemInfo systemInfo = systemInfoService.findSystemInfoById(id);
        return new Result<>(0, "ok", systemInfo);
    }

    @GetMapping("/systemInfo/ip")
    @ApiOperation(value = "查找机器信息", notes = "根据IP模糊查找机器信息", httpMethod = "GET")
    public Result findSystemInfoByIp(@RequestParam(value = "ip") String ip,
                                              @RequestParam(value = "page") int page,
                                              @RequestParam(value = "limit") int limit) {
        PageInfo<SystemInfo> list = systemInfoService.findSystemInfoIpLikeAndUser(ip, UserLocal.getCurrentUserId(), page, limit);
        return new Result<>(0, "ok", list.getList(), list.getTotal());
    }

    @PutMapping("/systemInfo/{id}/remark")
    @ApiOperation(value = "更新机器信息", notes = "更新机器信息备注", httpMethod = "PUT")
    public Result updateSystemInfoRemark(@PathVariable("id") Integer id, @RequestBody Map<String,Object> remark) {
        Integer flag = systemInfoService.updateSystemInfoRemarkById(id, (String) remark.get("remark"));
        return  resultByFlag(flag);
    }

    @PostMapping("/systemInfo")
    @ApiOperation(value = "新增机器信息", notes = "新增系统信息", httpMethod = "POST")
    public Result insertSystemInfo(@RequestBody SystemInfo systemInfo) {
        return Result.SUCCESS();
    }

    @DeleteMapping("/systemInfo/{id}")
    @ApiOperation(value = "删除机器信息", notes = "根据Id删除机器信息", httpMethod = "DELETE")
    public Result deleteSystemInfoById(@PathVariable("id") Integer id) {
        Integer flag = systemInfoService.deleteSystemInfoById(id);
        return  resultByFlag(flag);
    }

    @DeleteMapping("/systemInfo")
    @ApiOperation(value = "删除机器信息", notes = "根据Id数组删除机器信息", httpMethod = "DELETE")
    public Result deleteMachineByIds(@RequestBody Map<String, Object> delete) {
        Integer flag = systemInfoService.deleteSystemInfoByIds((List) delete.get("delete"));
        return  resultByFlag(flag);
    }

    @GetMapping("/systemInfo/detail/load/recent/{mid}")
    public String getRecentSystemLoad(@PathVariable("mid") String mid) {
        TimeStampData timeStampData = new TimeStampData();
        Long length = redisUtil.zZCard(DETECTOR_LOADINFO + mid);
        Set<String> set = redisUtil.zRangeByScore(DETECTOR_LOADINFO + mid, System.currentTimeMillis() - 60 * 1000, System.currentTimeMillis());

        List<Map> list = new ArrayList<>();

        for (String s : set) {
            Map<String, Map<String, Object>> result = new HashMap<>();
            Load load = gson.fromJson(s, Load.class);
            CpuVo cpuVo = voService.getCpuVo(load);
            MemoryVo memoryVo = voService.getMemoryVo(load);

            Map<String, Object> map = new HashMap<>();
            map.put("cpu", cpuVo);
            map.put("memory", memoryVo);
            result.put(String.valueOf(load.getCreateTimeMills()), map);
            list.add(result);
        }

        if (set.size() < 20) {
            int size = list.size();
            for (int i = 20 - size; i > 0; i--) {
                Map<String, Map<String, Object>> result = new HashMap<>();
                CpuVo cpuVo = voService.getDefaultCpuVo();
                MemoryVo memoryVo = voService.getDefaultMemoryVo();

                Map<String, Object> map = new HashMap<>();
                map.put("cpu", cpuVo);
                map.put("memory", memoryVo);
                result.put(String.valueOf(System.currentTimeMillis() - i * 3000), map);
                list.add(result);
            }
        }

        timeStampData.setData(list);
        return gson.toJson(timeStampData);
    }

    @GetMapping("/systemInfo/detail/load/realTime/{mid}")
    public String getRealTimeSystemLoad(@PathVariable("mid") String mid) {
        TimeStampData timeStampData = new TimeStampData();
        Long length = redisUtil.zZCard(DETECTOR_LOADINFO + mid);
        Set<String> set = redisUtil.zRangeByScore(DETECTOR_LOADINFO + mid, System.currentTimeMillis() - 3000, System.currentTimeMillis());


        CpuVo cpuVo;
        MemoryVo memoryVo;
        Load load = new Load();

        if (set.size() == 0) {
            cpuVo = voService.getDefaultCpuVo();
            memoryVo = voService.getDefaultMemoryVo();
        } else {
            load = gson.fromJson(set.toArray()[0].toString(), Load.class);
            cpuVo = voService.getCpuVo(load);
            memoryVo = voService.getMemoryVo(load);
        }

        Map<String, Map> result = new HashMap<>();
        Map<String, Object> map = new HashMap<>();

        map.put("cpu", cpuVo);
        map.put("memory", memoryVo);

        if (set.size() == 0)
            result.put(String.valueOf(System.currentTimeMillis()), map);
        else
            result.put(String.valueOf(load.getCreateTimeMills()), map);

        List<Map> list = new ArrayList<>();
        list.add(result);
        timeStampData.setData(list);
        return gson.toJson(timeStampData);
    }

}
