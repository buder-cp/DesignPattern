package com.example.hook_demo.base_reflect;

import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class StudentTest {

    public static void reflectTest() throws Exception {

        //获取私有构造方法
        Class<?> clazz = Class.forName("com.example.hook_demo.base_reflect.Student");
        Constructor<?> constructor = clazz.getDeclaredConstructor(int.class, String.class);
        constructor.setAccessible(true);

        //利用构造方法生成 Student 对象
        Object mStudent = constructor.newInstance(22, "大头");
        Log.d("log", "构造器是否报错：" + mStudent.toString());

        //获取 Student 对象的私有 age 属性
        Field mField = clazz.getDeclaredField("age");
        mField.setAccessible(true);
        int age = (int) mField.get(mStudent);
        Log.d("log", "年龄为:" + age);

        //调用 Student 对象的私有 getName 方法
        Method getMethod = clazz.getDeclaredMethod("getName");
        getMethod.setAccessible(true);
        String newName = (String) getMethod.invoke(mStudent);
        Log.d("log", "名字为:" + newName);

        //调用 Student 对象的静态 getStatic 方法
        Method getStaticMethod = clazz.getDeclaredMethod("getStatic");
        getStaticMethod.setAccessible(true);
        String result = (String) getStaticMethod.invoke(null);
        Log.d("log", "调用静态方法:" + result);

    }
}
