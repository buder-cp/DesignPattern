package com.dn_alan.myapplication;

import org.junit.Test;

public class DNThreadLocalTest {

    @Test
    public void test(){
        //---------------------------------------------------创建第一个threadLocal
        //创建一个本地线程（主线程）
        final ThreadLocal<String> threadLocal = new ThreadLocal<String>(){
            @Override
            protected String initialValue() {
                //重写初始化方法，默认返回null
                return "jett老师";
            }
        };

        System.out.println("主线程threadLocal：" + threadLocal.get());  //jett老师

        //---------------------------------------------------创建第二个threadLocal2
        final ThreadLocal<Integer> threadLocal2 = new ThreadLocal<>();

        //---------------------------------------------------thread-1
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //从ThreadLocalMap key=thread-1的值为空 ？ 没有值  就走 initialValue() 初始化方法
                String local1_value1 = threadLocal.get();
                Integer local2_value1 = threadLocal2.get();
                System.out.println("thread-1：" + "ThreadLocal1: " + local1_value1 + " ThreadLocal2:" + local2_value1);  //jett老师

                threadLocal.set("大卫老师");
                threadLocal2.set(10010);
                String local1_value2 = threadLocal.get();
                Integer local2_value2 = threadLocal2.get();
                System.out.println("thread-1：" + "ThreadLocal1: " + local1_value2 + " ThreadLocal2:" + local2_value2);  //大卫老师

                //避免大量无意义得内存占用
                threadLocal.remove();
            }
        });
        thread.start();

        //---------------------------------------------------thread-2
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                //从ThreadLocalMap key=thread-2得值 ？ 没有  就走 initialValue() 初始化方法
                String value1 = threadLocal.get();
                System.out.println("thread-2：" + value1);  //jett老师

                threadLocal.set("Alan老师");
                String value2 = threadLocal.get();
                System.out.println("thread-2：" + value2);  //Alan老师

                //避免大量无意义得内存占用
                threadLocal.remove();
            }
        });
        thread2.start();
    }
}
