package com.ryoua.service;

import com.ryoua.model.Load;
import com.ryoua.vo.CpuVo;
import com.ryoua.vo.MemoryVo;
import org.springframework.stereotype.Service;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/23
 **/
@Service
public class VoService extends BaseService {
    public CpuVo getCpuVo(Load load) {
        CpuVo cpuVo = new CpuVo();
        cpuVo.setCpuCores(load.getCpuLoad().getCpuCores());
        cpuVo.setCpuUsage(load.getCpuLoad().getCpuUsage());
        cpuVo.setCpuSystemUsage(load.getCpuLoad().getCpuSystemUsage());
        cpuVo.setCpuUserUsage(load.getCpuLoad().getCpuUserUsage());
        cpuVo.setCreateTimeMills(load.getCreateTimeMills());
        return cpuVo;
    }

    public MemoryVo getMemoryVo(Load load) {
        MemoryVo memoryVo = new MemoryVo();
        memoryVo.setMemorySize(load.getMemoryLoad().getMemorySize());
        memoryVo.setMemoryLess(load.getMemoryLoad().getMemoryLess());
        memoryVo.setMemoryUsage(load.getMemoryLoad().getMemoryUsage());
        memoryVo.setMemoryUse(load.getMemoryLoad().getMemoryUse());
        memoryVo.setCreateTimeMills(load.getCreateTimeMills());
        return memoryVo;
    }

    public CpuVo getDefaultCpuVo() {
        CpuVo cpuVo = new CpuVo();
        cpuVo.setCpuCores(0);
        cpuVo.setCpuUsage(0.0);
        cpuVo.setCpuSystemUsage(0.0);
        cpuVo.setCpuUserUsage(0.0);
        cpuVo.setCreateTimeMills(System.currentTimeMillis());
        return cpuVo;
    }

    public MemoryVo getDefaultMemoryVo() {
        MemoryVo memoryVo = new MemoryVo();
        memoryVo.setMemorySize(0L);
        memoryVo.setMemoryLess(0L);
        memoryVo.setMemoryUsage(0.0);
        memoryVo.setMemoryUse(0L);
        memoryVo.setCreateTimeMills(System.currentTimeMillis());
        return memoryVo;
    }
}
