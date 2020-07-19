package com.ryoua.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ryoua.mapper.SystemInfoMapper;
import com.ryoua.model.SystemInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * * @Author: RyouA
 * * @Date: 2020/7/19
 **/
@Service
public class SystemInfoService {
    @Autowired
    private SystemInfoMapper systemInfoMapper;

    public PageInfo<SystemInfo> getAllSystemInfoByUserName(String username, int page, int limit) {
        PageHelper.startPage(page, limit);
        PageInfo<SystemInfo> pageInfo = new PageInfo<>(systemInfoMapper.getAllSystemInfoByUserName(username));
        return pageInfo;
    }


}
