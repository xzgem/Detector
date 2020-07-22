package com.ryoua.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ryoua.mapper.MachineInfoMapper;
import com.ryoua.model.MachineInfo;
import com.ryoua.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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

    public PageInfo<MachineInfo> searchMachineInfoByIpAndMac(String ip, String mac, int page, int limit) {
        PageHelper.startPage(page, limit);
        ip = StringUtil.checkProperties(ip);
        mac = StringUtil.checkProperties(mac);
        PageInfo<MachineInfo> pageInfo = new PageInfo<>(machineInfoMapper.searchMachineInfoByIpAndMac("%" + ip + "%", "%" + mac + "%"));
        return pageInfo;
    }

    public MachineInfo getMachineInfoById(Integer id) {
        return machineInfoMapper.getMachineInfoById(id);
    }

    public boolean updateMachineInfoRemark(Integer id, String remark) {
        return machineInfoMapper.updateMachineInfoRemark(id, remark);
    }

    public void deleteMachineById(Integer id) {
        machineInfoMapper.deleteMachineById(id);
    }

    public void deleteMachineByIds(List ids) {
        String deleteIds = ids.toString();
        deleteIds = StringUtil.mybatisUseIn(deleteIds);
        machineInfoMapper.deleteMachineByIds(deleteIds);
    }

    public MachineInfo getMachineInfoByIpAndUserId(String ip, Integer userId) {
        return machineInfoMapper.getMachineInfoByIpAndUserId(ip, userId);
    }
}
