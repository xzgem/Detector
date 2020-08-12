package com.ryoua.service;

import com.ryoua.mapper.ContactMapper;
import com.ryoua.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/12
 **/
public class BaseService {
    @Autowired
    protected UserMapper userMapper;

    @Autowired
    protected ContactMapper contactMapper;
}
