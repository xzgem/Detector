package com.ryoua.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/19
 **/
@Controller
public class IndexController {
    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
