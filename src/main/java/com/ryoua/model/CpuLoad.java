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
@TableName("m_cpu_load")
public class CpuLoad {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String mid;

    private Integer cpuCores;

    private Double cpuUsage;
    private Double cpuSystemUsage;
    private Double cpuUserUsage;

    private String cpuUsageStr;
    private String cpuSystemUsageStr;
    private String cpuUseUsageStr;

    private String createTime;
    private Long createTimeMills;

    private Integer valid;
}
