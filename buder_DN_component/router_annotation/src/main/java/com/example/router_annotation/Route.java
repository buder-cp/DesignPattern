package com.example.router_annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//添加元注解
// @Target(ElementType.TYPE)   //接口、类、枚举、注解
// @Target(ElementType.FIELD) //字段、枚举的常量
// @Target(ElementType.METHOD) //方法
// @Target(ElementType.PARAMETER) //方法参数
// @Target(ElementType.CONSTRUCTOR)  //构造函数
// @Target(ElementType.LOCAL_VARIABLE)//局部变量
// @Target(ElementType.ANNOTATION_TYPE)//注解
// @Target(ElementType.PACKAGE) ///包 
@Target(ElementType.TYPE)
//注解的生命周期
//RetentionPolicy.SOURCE  源码阶段
//RetentionPolicy.CLASS   编译阶段
//RetentionPolicy.RUNTIME 运行阶段
@Retention(RetentionPolicy.CLASS)
public @interface Route {
    /**
     *路由的路径，标识一个路由节点
     */
    String path();

    /**
     * 将路由节点进行分组，可以实现按组动态加载
     */
    String group() default "";
}
