package com.ryoua.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/12
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemoryLoad {
    private Integer id;
    private String mid;

    private String memorySize;
    private String memoryUse;
    private String memoryLess;
    private String memoryUsage;

    private String updateTime;
    private Long updateTimeMills;

    private Integer valid;
}
