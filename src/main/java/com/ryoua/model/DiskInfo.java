package com.ryoua.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/1
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DiskInfo {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String mid;

    private String diskCapacity;
    private String diskUsage;
    private String diskName;

    private String updateTime;
    private Long updateTimeMills;
    private Integer valid;
}

