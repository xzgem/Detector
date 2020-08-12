package com.ryoua;

import com.ryoua.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/12
 **/
@RestController
public class Controller {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        userService.register("1", "1");
        return "123";
    }
}
