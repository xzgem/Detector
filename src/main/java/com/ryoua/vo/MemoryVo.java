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
public class MemoryVo {
    @SerializedName("memory_size")
    private Long memorySize;
    @SerializedName("memory_use")
    private Long memoryUse;
    @SerializedName("memory_less")
    private Long memoryLess;
    @SerializedName("memory_usage")
    private Double memoryUsage;
    @SerializedName("create_time_mills")
    private Long createTimeMills;
}
