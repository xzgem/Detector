package com.ryoua.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("m_process_load")
public class ProcessLoad {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String mid;

    private Integer pid;
    private String name;

    private Double cpuLoad;
    private Double memoryLoad;
    private String memoryUnit;

    private String updateTime;
    private Long updateTimeMills;
    private Integer valid;
}
