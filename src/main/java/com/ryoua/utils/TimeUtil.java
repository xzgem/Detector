package com.ryoua.utils;

import java.text.SimpleDateFormat;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/26
 **/
public class TimeUtil {
    public static String getNowTime(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
        return dateFormat.format(date);
    }
}
