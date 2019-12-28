package com.example.designpattern.proxy;

/**
 * 用户可以接触到的幕后的代理类
 */
public class StaticCinema implements Movie {

    RealMovie movie;

    public StaticCinema(RealMovie movie) {
        this.movie = movie;
    }

    @Override
    public void play() {
        //代理类的额外一些方法的插入
        lingShi();
        guangGao();

        //真正的幕后方法
        movie.play();
    }

    //静态代理需要为您做的额外工作
    private void lingShi() {
        System.out.println("静态代理：卖瓜子、饮料啦，挣点外快维持影院营业...");
    }

    //静态代理需要为您做的额外工作
    private void guangGao() {
        System.out.println("静态代理：正片前广告，挣点外快维持影院营业...");
    }
}
