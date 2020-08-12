package com.ryoua.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/18
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SystemInfo {
    @TableId(type = IdType.AUTO)
    private String id;
    private String mid;

    private String ip;
    private String host;

    private String osName;

    private String networkDetail;

    private Integer cpuCores;
    private String cpuDetail;

    private String memorySize;

    private String fileSystemDetail;
    private List<DiskInfo> diskInfos;

    private String updateTime;
    private Long updateTimeMills;

    private Integer autoRegister;
    private String remark;
    private Integer user;
    // 0:失效    1:有效
    private Integer valid;
    // 0:下线    1:在线
    private Integer status;
}
