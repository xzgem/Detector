package com.ryoua.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/12
 **/
@Data
@ToString
@TableName("m_memory_load")
public class MemoryLoad {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String mid;

    private Long memorySize;
    private Long memoryUse;
    private Long memoryLess;
    private Double memoryUsage;

    private String memorySizeStr;
    private String memoryUseStr;
    private String memoryLessStr;
    private String memoryUsageStr;

    private String createTime;
    private Long createTimeMills;

    private Integer valid;
}
