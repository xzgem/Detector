package com.ryoua.controller;

import com.google.gson.Gson;
import com.ryoua.vo.CpuVo;
import com.ryoua.vo.TimeStampData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/23
 **/
@RestController
public class TestController extends BaseController {
    @Autowired
    Gson gson;

    @GetMapping("/demo")
    public Integer demo() throws InterruptedException {
        Thread.sleep(100);
        return (int) (Math.random()  * 100);
    }



    public static void main(String[] args) throws InterruptedException {
        Long start = System.currentTimeMillis();
        Thread.sleep(1000);
        Long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
