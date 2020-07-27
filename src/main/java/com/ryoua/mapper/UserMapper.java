package com.ryoua.mapper;

import com.ryoua.model.Contact;
import com.ryoua.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/19
 **/
@Mapper
public interface UserMapper {

    @Select(value = "select * from auth_user where username = #{username} and valid = 1")
    User getUserByUserName(String username);
    @Select(value = "select * from auth_user where phone = #{phone} and valid = 1")
    User getUserByPhone(String phone);
    @Select(value = "select * from contact where user = #{user}")
    List<Contact> getContactByUser(Integer user);

    boolean updateContactByUser();

    @Insert(value = "insert into auth_user (username, password, update_time) VALUES \n" +
            "(#{username}, #{password}, current_timestamp)")
    boolean addUser(@Param("username") String username, @Param("password") String password);
    @Insert(value = "insert into contact (user, contact) values (#{user}, #{contact})")
    boolean addUserContact(@Param("user") int user, @Param("contact") String contact);
}
