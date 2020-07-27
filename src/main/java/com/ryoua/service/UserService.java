package com.ryoua.service;

import com.ryoua.mapper.UserMapper;
import com.ryoua.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public boolean login(String username ,String password) {
        User user = userMapper.getUserByUserName(username);
        if (user == null) {
            return false;
        }
        return password.equals(user.getPassword());
    }

    /**
     * 注册
     */
    public boolean register(String username ,String password) {
        return userMapper.addUser(username, password);
    }

    /**
     * 校验用户名是否存在
     */
    public User getUserByUserName(String userName) {
        return userMapper.getUserByUserName(userName);
    }

    public boolean isUserExist(String userName) {
        return getUserByUserName(userName) == null;
    }
}