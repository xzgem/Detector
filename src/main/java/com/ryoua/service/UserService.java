package com.ryoua.service;

import com.ryoua.mapper.UserMapper;
import com.ryoua.model.Contact;
import com.ryoua.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/19
 **/
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 登录
     */
    public Boolean login(String username ,String password) {
        User user = userMapper.getUserByUserName(username);
        if (user == null) {
            return false;
        }
        return password.equals(user.getPassword());
    }

    /**
     * 注册
     */
    public Boolean register(String username ,String password) {
        return userMapper.addUser(username, password);
    }

    /**
     * 校验用户名是否存在
     */
    public User getUserByUserName(String userName) {
        return userMapper.getUserByUserName(userName);
    }

    public Boolean isUserExist(String userName) {
        return getUserByUserName(userName) == null;
    }




    public Boolean deleteUserContact(Integer id) {
        return userMapper.deleteUserContact(id);
    }

    public Boolean updateUserContact(Integer id, String contact, Integer type) {
        return userMapper.updateUserContact(id, contact, type);
    }

    public Boolean addUserContact(Integer user, String contact, Integer type) {
        return userMapper.addUserContact(user, contact, type);
    }

    public List<Contact> getContactByUser(Integer user) {
        return userMapper.getContactByUser(user);
    }
}