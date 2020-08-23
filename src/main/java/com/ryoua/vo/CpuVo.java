package com.ryoua.vo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.ToString;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/23
 **/
@Data
@ToString
public class CpuVo {
    @SerializedName("cpu_cores")
    private Integer cpuCores;

    @SerializedName("cpu")
    private Double cpuUsage;
    @SerializedName("cpu_system")
    private Double cpuSystemUsage;
    @SerializedName("cpu_user")
    private Double cpuUserUsage;

    @SerializedName("create_time_mills")
    private Long createTimeMills;
}