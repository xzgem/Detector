package com.ryoua.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ryoua.mapper.ContactMapper;
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
public class UserService extends BaseService {

    /**
     * 登录
     */
    public Boolean login(String username ,String password) {
        User user = userMapper.selectOne(new QueryWrapper<User>().
                eq("username", username).
                eq("password", password));
        if (user == null) {
            return false;
        }
        return password.equals(user.getPassword());
    }

    /**
     * 注册
     */
    public Integer register(String username ,String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return userMapper.insert(user);
    }

    /**
     * 根据用户名获取用户
     */
    public User findUserByUserName(String userName) {
        return userMapper.selectOne(new QueryWrapper<User>().
                eq("username", userName));
    }

    /**
     * 检测用户是否存在
     */
    public Boolean isUserExist(String userName) {
        return findUserByUserName(userName) == null;
    }

}