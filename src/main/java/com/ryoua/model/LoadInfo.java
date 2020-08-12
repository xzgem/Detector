package com.ryoua.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/12
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoadInfo {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String mid;

    private CpuLoad cpuLoad;
    private MemoryLoad memoryLoad;
    private List<ProcessLoad> processLoads;
    private DockerInfo dockerInfo;
    private TrafficLoad trafficLoad;

    private String updateTime;
    private Long updateTimeMills;

    private Integer valid;
}

