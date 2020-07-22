package com.ryoua.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/22
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApiTestResult {
    public List<Integer> pings;
    public Integer median;

}
