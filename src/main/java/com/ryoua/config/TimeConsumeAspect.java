package com.ryoua.config;


import com.ryoua.controller.BaseController;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class TimeConsumeAspect extends BaseController {
    /**
     * 切点定义为注解@annotation(注解类路径)
     */
    @Pointcut("@annotation(com.ryoua.config.TimeConsume)")
    public void consume(){
    }

    @Around("consume()")
    public  <T> T around(ProceedingJoinPoint pjp) throws Throwable {
        Long startTime = System.currentTimeMillis();

        Object[] args = pjp.getArgs();
        T result;
        Method methodClass;
        try {

            result = (T)pjp.proceed(args);//执行方法

        }finally {
            long endTime = System.currentTimeMillis();
            Signature signature = pjp.getSignature();
            String methodName = signature.getName();
            Class<?> targetClass = pjp.getTarget().getClass();
            Class[] parameterTypes = ((MethodSignature) pjp.getSignature()).getParameterTypes();
            methodClass = targetClass.getMethod(methodName, parameterTypes);
            Annotation[] annotations = methodClass.getAnnotations();
            for (Annotation annotation : annotations){
                Class<? extends Annotation> aClass = annotation.annotationType();
                String simpleName = aClass.getSimpleName();
                if("TimeConsume".equals(simpleName)){
                    TimeConsume timeConsume = (TimeConsume) annotation;
                    String value = timeConsume.value();
                    logger.info(value+"[{}] 执行耗时：{}ms",methodName,endTime-startTime);
                    break;
                }
            }

        }

        return result;
    }

}
