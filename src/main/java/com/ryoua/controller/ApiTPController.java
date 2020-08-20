package com.ryoua.controller;


import com.ryoua.model.common.ConstantStatus;
import com.ryoua.model.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

/**
 * @author 晚吟
 */
@Api(value = "接口TP稳定性测试", tags = {"接口TP稳定性测试"})
@RestController
@RequestMapping(value = "/api")
public class ApiTPController extends BaseController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/tpTest")
    @ApiOperation(value = "Api接口TP测试", notes = "url,时间,请求类型,请求次数,请求参数必传", httpMethod = "GET")
    public Result apiTest(@RequestParam(value = "url", required = true) String url,
                          @RequestParam(value = "time", required = true) long time,
                          @RequestParam(value = "requestType", required = true) Integer requestType,
                          @RequestParam(value = "requestParam", required = true) String requestParam,
                          @RequestParam(value = "count", required = true) Integer count) {

        ResponseEntity<String> resp;
        ArrayList<Long> list = new ArrayList<>();
        ArrayList<Long> tpList = new ArrayList<>();
        long elapsedTime;
        int n = 0;
        //记录下每1分钟每一次请求调用的耗时
        long varTime = 0;
        for (int i = 0; i > 0; i++) {
            switch (requestType) {
                case 0:
                    long start = System.currentTimeMillis();
                    resp = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
                    n += 1;
                    long end = System.currentTimeMillis();
                    elapsedTime = end - start;
                    logger.info("=================耗时{}:=================" + (end - start) + "ms");
                    varTime += elapsedTime;
                    list.add(elapsedTime);
                    //响应时间满1分钟停止
                    if (varTime == 60000) {
                        break;
                    }
                case 1:
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    HttpEntity<String> entity = new HttpEntity<>(requestParam, headers);
                    //记录下每一次请求调用的耗时
                    long start1 = System.currentTimeMillis();
                    resp = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
                    n += 1;
                    long end1 = System.currentTimeMillis();
                    elapsedTime = end1 - start1;
                    logger.info("=================耗时{}:=================" + (end1 - start1) + "ms");
                    list.add(elapsedTime);
                    //响应时间满1分钟停止
                    if (varTime == 60000) {
                        break;
                    }
            }
            //计算每分钟的TP99百分位线
            Collections.sort(list);
            double tp99;
            tp99 = n * 0.99;
            tpList.add(new Double(tp99).longValue());
            //响应时间满就停止
            if (varTime == time){
                break;
            }
        }
            return Result.SUCCESS(tpList);
    }

}
