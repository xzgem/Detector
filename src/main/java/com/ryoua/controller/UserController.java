package com.ryoua.controller;

import com.ryoua.config.JWTIgnore;
import com.ryoua.handler.UserLocal;
import com.ryoua.model.Contact;
import com.ryoua.model.User;
import com.ryoua.model.common.Result;
import com.ryoua.model.common.ResultCode;
import com.ryoua.utils.TokenUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/19
 **/
@Slf4j
@RestController
public class UserController extends BaseController {
    @PostMapping("/login")
    @JWTIgnore
    @ApiOperation(value = "登录", notes = "登录", tags = "user", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "账号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String"),
    })
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
    @JWTIgnore
    @ApiOperation(value = "注册", notes = "注册", tags = "user", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "账号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String"),
    })
    public Result register(@RequestBody User user) {
        if (userService.isUserExist(user.getUsername())) {
            return new Result(ResultCode.USER_IS_EXIST);
        }
        boolean register = userService.register(user.getUsername(), user.getPassword());
        if (register) {
            return Result.SUCCESS();
        } else {
            return Result.FAIL();
        }
    }

    @GetMapping("/contact")
    @ApiOperation(value = "获取预警通知方式", notes = "获取预警通知方式", tags = "contact", httpMethod = "GET")
    @ApiImplicitParams({
    })
    public Result findContact() {
        Integer uid = 1;
        List<Contact> list = userService.getContactByUser(uid);
        for (Contact contact : list) {
            if (contact.getType() == 0)
                contact.setTypeStr("手机");
            else
                contact.setTypeStr("邮箱");
        }
        return Result.SUCCESS(list);
    }

    @PutMapping("/contact")
    @ApiOperation(value = "更新手机/邮箱", notes = "更新手机/邮箱", tags = "contact", httpMethod = "PUT")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "contact", value = "手机/邮箱", required = true, dataType = "String"),
            @ApiImplicitParam(name = "type", value = "手机或邮箱对应的type", required = true, dataType = "Integer"),
    })
    public Result updateContact(@RequestBody Contact contact) {
        Boolean flag = userService.updateUserContact(contact.getId(), contact.getContact(), contact.getType());
        return resultByFlag(flag);
    }

    @DeleteMapping("/contact/{id}")
    @ApiOperation(value = "删除手机/邮箱", notes = "删除手机/邮箱", tags = "contact", httpMethod = "DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "手机/邮箱的Id", required = true, dataType = "Integer"),
    })
    public Result deleteContact(@PathVariable("id") Integer id) {
        Boolean flag = userService.deleteUserContact(id);
        return resultByFlag(flag);
    }

    @PostMapping("/contact")
    @ApiOperation(value = "添加手机/邮箱", notes = "添加手机/邮箱", tags = "contact", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "contact", value = "手机/邮箱", required = true, dataType = "String"),
            @ApiImplicitParam(name = "type", value = "手机或邮箱对应的type", required = true, dataType = "Integer"),
    })
    public Result insertContact(@RequestBody Contact contact) {
        Integer uid = 1;
        Boolean flag = userService.addUserContact(uid, contact.getContact(), contact.getType());
        return resultByFlag(flag);
    }

}
