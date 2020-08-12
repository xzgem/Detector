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
@TableName("m_docker_info")
public class DockerInfo {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String mid;

    private String dockerName;
    private String dockerId;
    private String dockerCpuUsage;
    private String dockerMemoryUsage;

    private String updateTime;
    private Long updateTimeMills;

    private Integer valid;
}
