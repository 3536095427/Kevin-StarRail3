package com.atguigu.commonservice.utils;


import com.atguigu.model.pojo.UserBasic;

public class AuthContext {
    private static final ThreadLocal<UserBasic> threadLocal = new ThreadLocal<>();

    public static void setAuthContext(UserBasic userBasic) {
        threadLocal.set(userBasic);
    }

    public static UserBasic getAuthContext() {
        return threadLocal.get();
    }

    public static void removeAuthContext() {
        threadLocal.remove();
    }

}
