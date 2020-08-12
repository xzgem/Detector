package com.ryoua.model;

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
public class TrafficLoad {
    private Integer id;
    private String mid;

    private Long sendPackets;
    private Long receivePackets;

    private String downLoadSpeed;
    private String uploadSpeed;

    private String updateTime;
    private Long updateTimeMills;
    private Integer valid;
}
