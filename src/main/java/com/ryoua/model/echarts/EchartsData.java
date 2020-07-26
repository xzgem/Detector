package com.ryoua.model.echarts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/26
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EchartsData {
    private String name;
    private String type;
    private String stack;
    private Map areaStyle;
    private Object[] data;
}
