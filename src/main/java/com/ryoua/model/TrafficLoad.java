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
 * * @Date: 2020/8/1
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("m_trafiic_load")
public class TrafficLoad {
    @TableId(type = IdType.AUTO)
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
