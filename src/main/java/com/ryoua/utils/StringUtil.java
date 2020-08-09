package com.ryoua.utils;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/18
 **/
public class StringUtil {

    public final static String getUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String mybatisUseIn(String string) {
        string = "(" + string + ")";
        string = string.replace("[", "");
        string = string.replace("]", "");
        return string;
    }

    public static String turnNullToBlank(String string) {
        if (string == null || string.equals(""))
            return "";
        else
            return string;
    }

    public static String checkProperties(String string) {
        string = turnNullToBlank(string);
        if (string.equals(" "))
            string = "";
        return string;
    }

    /**
     * 随机6位验证码生成
     */
    static final String VERIFY_CODES = "1234567890";

    /**
     * 使用系统默认字符源生成验证码
     * @param verifySize    验证码长度
     * @return
     */
    public static String generateVerifyCode(int verifySize){
        return generateVerifyCode(verifySize, VERIFY_CODES);
    }

    /**
     * 使用指定源生成验证码
     * @param verifySize    验证码长度
     * @param sources   验证码字符源
     * @return
     */
    public static String generateVerifyCode(int verifySize, String sources){
        if(sources == null || sources.length() == 0){
            sources = VERIFY_CODES;
        }
        int codesLen = sources.length();
        Random rand = new Random(System.currentTimeMillis());
        StringBuilder verifyCode = new StringBuilder(verifySize);
        for(int i = 0; i < verifySize; i++){
            verifyCode.append(sources.charAt(rand.nextInt(codesLen-1)));
        }
        return verifyCode.toString();
    }
    // 生成随机盐
    public static String getRandomSalt() {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789/.,'];[=-";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

}
