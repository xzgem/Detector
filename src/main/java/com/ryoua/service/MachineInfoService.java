package com.ryoua.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ryoua.mapper.MachineInfoMapper;
import com.ryoua.model.MachineInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * * @Author: RyouA
 * * @Date: 2020/7/19
 **/
@Service
public class MachineInfoService {
    @Autowired
    private MachineInfoMapper machineInfoMapper;

    public PageInfo<MachineInfo> getAllMachineInfoByUserName(String username, int page, int limit) {
        PageHelper.startPage(page, limit);
        PageInfo<MachineInfo> pageInfo = new PageInfo<>(machineInfoMapper.getAllMachineInfoByUserName(username));
        return pageInfo;
    }

    public MachineInfo getMachineInfoById(Integer id) {
        return machineInfoMapper.getMachineInfoById(id);
    }

    public void deleteMachineById(Integer id) {
        machineInfoMapper.deleteMachineById(id);
    }

    public MachineInfo getMachineInfoByIpAndUserId(String ip, Integer userId) {
        return machineInfoMapper.getMachineInfoByIpAndUserId(ip, userId);
    }
}
