package com.ryoua.controller;

import com.google.gson.Gson;
import com.ryoua.model.common.Audience;
import com.ryoua.model.common.Result;
import com.ryoua.service.ContactService;
import com.ryoua.service.SystemInfoService;
import com.ryoua.service.UserService;
import com.ryoua.utils.RedisUtil;
import com.ryoua.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/22
 **/
@Slf4j
public class BaseController {

    protected Logger logger = null;

    static final String DETECTOR_APIINFO = "detector:apiinfo:";
    static final String DETECTOR_SYSTEMINFO = "detector:machineinfo:";
    static final String DETECTOR_LOADINFO = "detector:loadinfo:";

    @Autowired
    Gson gson;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    SystemInfoService systemInfoService;
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    Audience audience;
    @Autowired
    UserService userService;
    @Autowired
    ContactService contactService;
    @Autowired
    RestTemplate restTemplate;

    protected Result resultByFlag(Integer flag) {
        if (flag >= 1) {
            return Result.SUCCESS();
        } else {
            return Result.FAIL();
        }
    }
}
