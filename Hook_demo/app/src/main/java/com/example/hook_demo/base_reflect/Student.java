package com.example.hook_demo.base_reflect;

public class Student {

    private int age;

    private String name;

    private static String mStatic;

    public Student() {

    }

    private Student(int age, String name) {
        this.age = age;
        this.name = name;
        mStatic = "静态反射";
    }

    private String getName() {
        return name;
    }

    private static String getStatic() {
        return mStatic;
    }

}
