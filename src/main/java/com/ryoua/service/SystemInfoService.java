package com.ryoua.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ryoua.mapper.SystemInfoMapper;
import com.ryoua.model.SystemInfo;
import com.ryoua.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * * @Author: RyouA
 * * @Date: 2020/7/19
 **/
//@Service
//public class SystemInfoService {
//    @Autowired
//    private SystemInfoMapper systemInfoMapper;
//
//    public PageInfo<SystemInfo> findAllMachineInfoByUserName(String username, int page, int limit) {
//        PageHelper.startPage(page, limit);
//        PageInfo<SystemInfo> pageInfo = new PageInfo<>(systemInfoMapper.findAllMachineInfoByUserName(username));
//        return pageInfo;
//    }
//
//    public SystemInfo getMachineinfoByIp(String ip) {
//        return systemInfoMapper.getMachineinfoByIp(ip);
//    }
//
//    public PageInfo<SystemInfo> findMachineInfoByIp(String ip, int page, int limit) {
//        PageHelper.startPage(page, limit);
//        ip = StringUtil.checkProperties(ip);
//        PageInfo<SystemInfo> pageInfo = new PageInfo<>(systemInfoMapper.findMachineInfoByIp("%" + ip + "%"));
//        return pageInfo;
//    }
//
//    public SystemInfo findMachineInfoById(Integer id) {
//        return systemInfoMapper.findMachineInfoById(id);
//    }
//
//    public boolean updateMachineInfoRemark(Integer id, String remark) {
//        return systemInfoMapper.updateMachineInfoRemark(id, remark);
//    }
//
//    public void deleteMachineById(Integer id) {
//        systemInfoMapper.deleteMachineById(id);
//    }
//
//    public void deleteMachineByIds(List ids) {
//        String deleteIds = ids.toString();
//        deleteIds = StringUtil.mybatisUseIn(deleteIds);
//        systemInfoMapper.deleteMachineByIds(deleteIds);
//    }
//
////    public boolean addMachine(MachineInfo machineInfo) {
////        return machineInfoMapper.addMachineInfo(machineInfo.getUser(),
////                machineInfo.getIp(),
////                machineInfo.getHost(),
////                machineInfo.getOsName(),
////                machineInfo.getOsVersion(),
////                machineInfo.getMac());
////    }
//
//    public SystemInfo getMachineInfoByIpAndUserId(String ip, Integer userId) {
//        return systemInfoMapper.getMachineInfoByIpAndUserId(ip, userId);
//    }
//}
