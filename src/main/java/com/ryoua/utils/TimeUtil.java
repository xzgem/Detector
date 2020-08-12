package com.ryoua.utils;

import java.text.SimpleDateFormat;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/26
 **/
public class TimeUtil {
    public static String getTimeHMS(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
        return dateFormat.format(date);
    }

    public static String getTimeMS(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
        return dateFormat.format(date);
    }

    public static String getTimeS(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("s");
        return dateFormat.format(date);
    }
}
