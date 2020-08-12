package com.ryoua.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class CpuLoad {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String mid;

    private Integer cpuCores;
    private String cpuSystemUse;
    private String cpuUserUse;
    private String cpuUsage;

    private String updateTime;
    private Long updateTimeMills;

    private Integer valid;
}
