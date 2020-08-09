package com.ryoua.handler;

import com.ryoua.model.User;

import javax.servlet.http.HttpServletRequest;

/**  登录保存/获取用户信息
 * * @Author: RyouA
 * * @Date: 2020/7/19
 **/
public class UserLocal {
    private static final ThreadLocal<Integer> userIdLocal = new ThreadLocal<>();

    private static final ThreadLocal<User> userLocal = new ThreadLocal<>();

    public static void setUserId(Integer userId){
        userIdLocal.set(userId);
    }

    public static void setUser(User user){
        userLocal.set(user);
    }


    public static Integer getCurrentUserId(){
        return userIdLocal.get();
    }

    public static User getCurrentUser(User user){
        return userLocal.get();
    }

}
