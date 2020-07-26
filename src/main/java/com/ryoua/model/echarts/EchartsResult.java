package com.ryoua.model.echarts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/26
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EchartsResult {
    private EchartsData[] echartsData;
    private Object[] xray;
    private String yray;
}
