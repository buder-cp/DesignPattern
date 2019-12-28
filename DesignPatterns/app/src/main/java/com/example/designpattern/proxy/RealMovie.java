package com.example.designpattern.proxy;


/**
 * 真正播放的电影
 */
public class RealMovie implements Movie{
    @Override
    public void play() {
        System.out.println("幕后真正执行：正在为您播放《唐人街探案2》");
        System.out.println("************************");
    }
}