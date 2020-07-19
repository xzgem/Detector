package com.ryoua.mapper;

import com.ryoua.model.SystemInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/19
 **/
@Mapper
public interface SystemInfoMapper {
    @Select(value = "select * from sys_server")
    List<SystemInfo> getAllSystemInfoByUserName(String username);
}
