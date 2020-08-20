package com.ryoua.config;


import java.lang.annotation.*;

/**
 * @author 晚吟
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TimeConsume {

    String value() default "方法";

}
