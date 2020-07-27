package com.ryoua.controller;

import com.ryoua.model.ApiTest;
import com.ryoua.model.common.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/22
 **/
@RestController
public class ApiController extends BaseController {
    @PostMapping("/apiTest")
    public Result apiTest(@RequestBody ApiTest apiTest) {
        List<Long> list = new ArrayList<>();
        apiTestService.setApiTest(apiTest);
        apiTestService.setList(list);
        new Thread(apiTestService).start();
        return Result.SUCCESS();
    }
}
