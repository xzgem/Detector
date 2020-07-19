package com.ryoua.mapper;

import com.ryoua.model.User;
import org.apache.ibatis.annotations.*;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/19
 **/
@Mapper
public interface UserMapper {

    @Select(value = "select * from auth_user where username = #{username} and valid = 1")
    User getUserByUserName(String username);


    @Insert(value = "insert into auth_user (id, username, password, update_time, salt) VALUES \n" +
            "(#{id}, #{username}, #{password}, current_timestamp, #{salt})")
    boolean addUser(@Param("id") String id, @Param("username") String username, @Param("password") String password, @Param("salt") String salt);

    @Select(value = "select * from auth_user where phone = #{phone} and valid = 1")
    User getUserByPhone(String phone);
}
