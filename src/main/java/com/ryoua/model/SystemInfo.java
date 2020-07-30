package com.ryoua.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/18
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SystemInfo {
    private String ip;
    private String host;
    private String oid;
    private String mac;
    private String networkDetail;

    private String osName;
    private String osDetail;

    private long cpuCores;
    private String cpuDetail;

    private long memory;
    private String memoryDetail;

    private long disk;
    private String diskDetail;

    private String fileDetail;
    private String updateTime;
    private boolean autoRegister;
    private String remark;
    private int user;
    private int valid;

    public boolean getAutoRegister() {
        return autoRegister;
    }
}
