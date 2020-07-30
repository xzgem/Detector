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

    @Insert(value = "insert into auth_user (username, password, update_time) VALUES \n" +
            "(#{username}, #{password}, current_timestamp)")
    Boolean addUser(@Param("username") String username, @Param("password") String password);




    @Insert(value = "insert into contact (user, contact, type) values (#{user}, #{contact}, #{type})")
    Boolean addUserContact(@Param("user") Integer user, @Param("contact") String contact, @Param("type") Integer type);

    @Select(value = "select * from contact where user = #{user} and valid = 1")
    List<Contact> getContactByUser(Integer user);

    @Delete(value = "update  contact set valid = 0 where id = #{id}")
    Boolean deleteUserContact(Integer id);

    @Update(value = "update contact set contact = #{contact}, type = #{type} where id = #{id}")
    Boolean updateUserContact(@Param("id") Integer id, @Param("contact") String contact, @Param("type") Integer type);
}
