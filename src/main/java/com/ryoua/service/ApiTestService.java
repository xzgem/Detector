package com.ryoua.service;

import com.ryoua.model.ApiTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/22
 **/
@Service
public class ApiTestService implements Runnable{
    @Autowired
    RestTemplate restTemplate;

    private ApiTest apiTest;

    private List<Long> list;

    public void setApiTest(ApiTest apiTest) {
        this.apiTest = apiTest;
    }

    public void setList(List<Long> list) {
        this.list = list;
    }

    public void apiTest() {
        for (int i = 0; i < 1000; i++) {
            long start = System.currentTimeMillis();
            restTemplate.getForObject(apiTest.getUrl(), String.class);
            long end = System.currentTimeMillis();
            long time = end - start;
            list.add(time);
        }
    }

    @Override
    public void run() {
        apiTest();
    }
}
