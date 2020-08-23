package com.ryoua.model;

import lombok.Data;
import lombok.ToString;


/**
 * * @Author: RyouA
 * * @Date: 2020/8/12
 **/
@Data
@ToString
public class Load {
    private CpuLoad cpuLoad;
    private MemoryLoad memoryLoad;
    private Long createTimeMills;
}

