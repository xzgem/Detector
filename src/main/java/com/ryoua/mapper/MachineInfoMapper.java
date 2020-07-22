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

    @Select(value = "select * from sys_machine where ip like #{ip} and mac like #{mac} and valid = 1")
    List<MachineInfo> searchMachineInfoByIpAndMac(@Param("ip") String ip, @Param("mac") String mac);

    @Update(value = "update sys_machine set valid = 0 where id = #{id}")
    void deleteMachineById(Integer id);

    @Update(value = "update sys_machine set valid = 0 where id in ${deleteIds}")
    void deleteMachineByIds(@Param("deleteIds") String deleteIds);

    @Update(value = "update sys_machine set remark = #{remark} where id = #{id}")
    boolean updateMachineInfoRemark(@Param("id") Integer id, @Param("remark") String remark);

    @Select(value = "select * from sys_machine where ip = #{ip} and uid = #{userId} and valid = 1")
    MachineInfo getMachineInfoByIpAndUserId(@Param("ip") String ip, @Param("userId") Integer userId);

    @Select(value = "select * from sys_machine where id = #{id} and valid = 1")
    MachineInfo getMachineInfoById(Integer id);

    void addMachine();
}
