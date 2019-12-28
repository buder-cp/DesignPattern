package com.example.designpattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicCinema implements InvocationHandler {

    private Object obj;

    public DynamicCinema(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //动态代理需要为您做的额外工作

        if (method.getName().equals("play")) {
            System.out.println("动态代理：卖瓜子、饮料啦，挣点外快维持影院营业...");
            System.out.println("动态代理：正片前广告，挣点外快维持影院营业...");
        }

        if (method.getName().equals("anMo")) {
            System.out.println("动态代理：推销精油，以维持影院营业...");
            System.out.println("动态代理：推销服务，以维持影院营业...");
        }

        Object result = method.invoke(obj, args);
        return result;
    }
}
