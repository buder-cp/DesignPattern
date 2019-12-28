package com.example.designpattern.proxy;

public class BingBingDaBaoJian implements DaBaoJian {
    @Override
    public void anMo() {
        System.out.println("幕后真正执行：头牌按摩师冰冰正在服务");
    }
}
