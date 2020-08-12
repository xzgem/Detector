package com.ryoua.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/18
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SystemInfo {
    private String id;
    private String mid;

    private String ip;
    private String host;

    private String osName;

    private String networkDetail;

    private Integer cpuCores;
    private String cpuDetail;

    private String memorySize;

    private String fileSystemDetail;
    private List<DiskInfo> diskInfos;

    private String updateTime;
    private Long updateTimeMills;

    private Integer autoRegister;
    private String remark;
    private Integer user;
    private Integer valid;
}
