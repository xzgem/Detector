package com.ryoua.mapper;

import com.ryoua.model.MachineInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/19
 **/
@Mapper
public interface MachineInfoMapper {
    @Select(value = "select * from sys_machine where valid = 1")
    List<MachineInfo> getAllMachineInfoByUserName(String username);

    @Update(value = "update sys_machine set valid = 0 where id = #{id}")
    void deleteMachineById(Integer id);

    @Select(value = "select * from sys_machine where ip = #{ip} and uid = #{userId}")
    MachineInfo getMachineInfoByIpAndUserId(String ip, Integer userId);

    void addMachine();
}
