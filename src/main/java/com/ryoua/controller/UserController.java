package com.ryoua.controller;

import com.ryoua.config.JWTIgnore;
import com.ryoua.model.User;
import com.ryoua.model.common.Result;
import com.ryoua.model.common.ResultCode;
import com.ryoua.service.UserService;
import com.ryoua.utils.TokenUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/19
 **/
@Slf4j
@Controller
public class UserController {
    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String Login() {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    @JWTIgnore
    public Result adminLogin(@RequestBody User user) {
        if (!userService.login(user.getUsername(), user.getPassword())) {
            return Result.FAIL();
        }
        String userId = UUID.randomUUID().toString();
        return Result.SUCCESS();
    }

    @PostMapping("/register")
    @ResponseBody
    @JWTIgnore
    public Result register(@RequestBody User user) {
        User user1 = userService.getUserByUserName(user.getUsername());
        if (user1 != null)
            return new Result(ResultCode.USER_IS_EXIST);
        boolean register = userService.register(user.getUsername(), user.getPassword());
        if (register)
            return Result.SUCCESS();
        else
            return Result.FAIL();
    }
}
