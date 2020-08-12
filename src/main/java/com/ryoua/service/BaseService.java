package com.ryoua.service;

import com.ryoua.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/12
 **/
public class BaseService {
    @Autowired
    protected UserMapper userMapper;

    @Autowired
    protected ContactMapper contactMapper;

    @Autowired
    protected SystemInfoMapper systemInfoMapper;

    @Autowired
    protected ApiInfoMapper apiInfoMapper;

    @Autowired
    protected CpuLoadMapper cpuLoadMapper;

    @Autowired
    protected DiskInfoMapper diskInfoMapper;

    @Autowired
    protected DockerInfoMapper dockerInfoMapper;

    @Autowired
    protected MemoryLoadMapper memoryLoadMapper;

    @Autowired
    protected ProcessLoadMapper processLoadMapper;

    @Autowired
    protected TrafficLoadMapper trafficLoadMapper;
}
