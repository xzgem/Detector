package com.ryoua.controller;

import cn.hutool.json.JSONObject;
import com.google.gson.Gson;
import com.ryoua.config.JWTIgnore;
import com.ryoua.model.User;
import com.ryoua.model.common.Audience;
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

import javax.servlet.http.HttpServletResponse;
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
    private Audience audience;

    @Autowired
    private UserService userService;

    @Autowired
    private Gson gson;

    @PostMapping("/login")
    @ResponseBody
    @JWTIgnore
    public Result login(HttpServletResponse response, @RequestBody User user) {
        User user1 = userService.getUserByUserName(user.getUsername());
        if (user1 == null || (!user1.getPassword().equals(user.getPassword()))) {
            return new Result(ResultCode.USER_LOGIN_ERROR);
        }

        String token = TokenUtil.createJWT(String.valueOf(user1.getId()), user.getUsername(), audience);
        log.info("### 登录成功, token={} ###", token);
        return Result.SUCCESS(token);
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
