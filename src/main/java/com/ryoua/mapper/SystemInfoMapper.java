package com.ryoua.mapper;

import com.ryoua.model.SystemInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/19
 **/
@Mapper
public interface SystemInfoMapper {
    @Select(value = "select * from sys_machine where valid = 1")
    List<SystemInfo> getAllMachineInfoByUserName(String username);

    @Select(value = "select * from sys_machine where ip like #{ip} and mac like #{mac} and valid = 1")
    List<SystemInfo> searchMachineInfoByIpAndMac(@Param("ip") String ip, @Param("mac") String mac);

    @Select(value = "select * from sys_machine where ip = #{ip} and valid = 1")
    SystemInfo getMachineinfoByIp(String ip);

    @Update(value = "update sys_machine set valid = 0 where id = #{id}")
    void deleteMachineById(Integer id);

    @Update(value = "update sys_machine set valid = 0 where id in ${deleteIds}")
    void deleteMachineByIds(@Param("deleteIds") String deleteIds);

    @Update(value = "update sys_machine set remark = #{remark} where id = #{id}")
    boolean updateMachineInfoRemark(@Param("id") Integer id, @Param("remark") String remark);

    @Select(value = "select * from sys_machine where ip = #{ip} and uid = #{userId} and valid = 1")
    SystemInfo getMachineInfoByIpAndUserId(@Param("ip") String ip, @Param("userId") Integer userId);

    @Select(value = "select * from sys_machine where id = #{id} and valid = 1")
    SystemInfo getMachineInfoById(Integer id);

    @Insert(value = "insert into sys_machine (user, ip, host, os_name, os_version, mac) VALUES" +
            "(user, ip, host, os_name, os_version, mac)")
    boolean addMachineInfo(int user, String ip, String host, String osName, String osVersion, String mac);

    void addMachine();
}
