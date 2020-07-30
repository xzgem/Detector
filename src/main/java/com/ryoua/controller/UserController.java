package com.ryoua.controller;

import com.ryoua.config.JWTIgnore;
import com.ryoua.handler.UserLocal;
import com.ryoua.model.Contact;
import com.ryoua.model.User;
import com.ryoua.model.common.Result;
import com.ryoua.model.common.ResultCode;
import com.ryoua.utils.TokenUtil;
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
public class UserController extends BaseController{
    @PostMapping("/login")
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
    @JWTIgnore
    public Result register(@RequestBody User user) {
        if (userService.isUserExist(user.getUsername()))
            return new Result(ResultCode.USER_IS_EXIST);
        boolean register = userService.register(user.getUsername(), user.getPassword());
        if (register)
            return Result.SUCCESS();
        else
            return Result.FAIL();
    }

    @GetMapping("/contact")
    public Result addContact() {
        Integer uid = 1;
        List<Contact> list = userService.getContactByUser(uid);;
        for (Contact contact : list) {
            if (contact.getType() == 0)
                contact.setTypeStr("手机");
            else
                contact.setTypeStr("邮箱");
        }
        return Result.SUCCESS(list);
    }

    @PutMapping("/contact")
    public Result updateContact(@RequestBody Contact contact) {
        Boolean flag = userService.updateUserContact(contact.getId(), contact.getContact(), contact.getType());
        return resultByFlag(flag);
    }

    @DeleteMapping("/contact/{id}")
    public Result deleteContact(@PathVariable("id") Integer id) {
        Boolean flag = userService.deleteUserContact(id);
        return resultByFlag(flag);
    }

    @PatchMapping("/contact")
    public Result addContact(@RequestBody Contact contact) {
        Integer uid = UserLocal.getCurrentUserId();
        Boolean flag = userService.addUserContact(uid, contact.getContact(), contact.getType());
        return resultByFlag(flag);
    }

}
