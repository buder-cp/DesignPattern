package com.dn_alan.myapplication.aspect;

import android.os.SystemClock;
import android.util.Log;

import com.dn_alan.myapplication.annotation.BehaviorTrace;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.Random;

@Aspect
public class BehaviorTraceAspect {
    //定义切面的规则
    //1、就再原来的应用中那些注解的地方放到当前切面进行处理
    //execution（注解名   注解用的地方）
    @Pointcut("execution(@com.dn_alan.myapplication.annotation.BehaviorTrace *  *(..))")
    public void methodAnnottatedWithBehaviorTrace() {
    }


    //2、对进入切面的内容如何处理
    //@Before 在切入点之前运行
//    @After("methodAnnottatedWithBehaviorTrace()")
    //@Around 在切入点前后都运行
    @Around("methodAnnottatedWithBehaviorTrace()")
    public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        String value = methodSignature.getMethod().getAnnotation(BehaviorTrace.class).value();

        long begin = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        SystemClock.sleep(500);
        long duration = System.currentTimeMillis() - begin;
        Log.d("alan", String.format("%s功能：%s类的%s方法执行了，用时%d ms",
                value, className, methodName, duration));
        return result;
    }
}
