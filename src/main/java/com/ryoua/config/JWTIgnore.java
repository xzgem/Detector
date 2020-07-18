package com.ryoua.config;

import java.lang.annotation.*;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/18
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JWTIgnore {
}
