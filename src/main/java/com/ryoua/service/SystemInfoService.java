package com.ryoua.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
@Service
public class SystemInfoService extends BaseService {
    public PageInfo<SystemInfo> findAllSystemInfoByUser(Integer user, int page, int limit) {
        PageHelper.startPage(page, limit);
        PageInfo<SystemInfo> pageInfo = new PageInfo<>(systemInfoMapper.selectList(new QueryWrapper<SystemInfo>()
                .eq("user", user)
                .eq("valid", 1)));
        return pageInfo;
    }

    public SystemInfo findSystemInfoByIpAndUser(String ip, Integer user) {
        return systemInfoMapper.selectOne(new QueryWrapper<SystemInfo>()
        .eq("ip", ip)
        .eq("user", user)
        .eq("valid", 1));
    }

    public PageInfo<SystemInfo> findSystemInfoIpLikeAndUser(String ip, Integer user, int page, int limit) {
        PageHelper.startPage(page, limit);
        ip = StringUtil.checkProperties(ip);
        PageInfo<SystemInfo> pageInfo = new PageInfo<>(systemInfoMapper.selectList(new QueryWrapper<SystemInfo>()
                .like("ip", "%" + ip + "%")
                .eq("user", user)
                .eq("valid", 1)));
        return pageInfo;
    }

    public SystemInfo findSystemInfoById(Integer id) {
        return systemInfoMapper.selectOne(new QueryWrapper<SystemInfo>()
        .eq("id", id)
        .eq("valid", 1));
    }

    public Integer updateSystemInfoRemarkById(Integer id, String remark) {
        SystemInfo systemInfo = new SystemInfo();
        systemInfo.setId(id);
//        systemInfo.setRemark(remark);
        return systemInfoMapper.updateById(systemInfo);
    }

    public Integer deleteSystemInfoById(Integer id) {
        SystemInfo systemInfo = new SystemInfo();
        systemInfo.setId(id);
        systemInfo.setValid(0);
        return systemInfoMapper.updateById(systemInfo);
    }

    public Integer deleteSystemInfoByIds(List ids) {
        SystemInfo systemInfo = new SystemInfo();
        systemInfo.setValid(0);
        return systemInfoMapper.update(systemInfo, new QueryWrapper<SystemInfo>().in("id", ids));
    }

    public Integer insertSystemInfo(SystemInfo systemInfo) {
        return systemInfoMapper.insert(systemInfo);
    }
}
