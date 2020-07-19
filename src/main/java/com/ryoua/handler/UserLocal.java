package com.ryoua.handler;

import com.ryoua.model.User;

import javax.servlet.http.HttpServletRequest;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/19
 **/
public class UserLocal {
    private static final ThreadLocal<User> userLocal = new ThreadLocal<User>();

    private static final ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<>();

    public static void add(User sysUser){
        userLocal.set(sysUser);
    }

    public static void add(HttpServletRequest request){
        requestHolder.set(request);
    }

    public static User getCurrentUser(){
        return userLocal.get();
    }

    public static HttpServletRequest getCurrentRequest(){
        return requestHolder.get();
    }

    public static void remove(){
        userLocal.remove();
        requestHolder.remove();
    }
}
