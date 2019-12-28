package com.example.designpattern.proxy;

import java.lang.reflect.Proxy;

/**
 * 静态代理测试
 */
public class StaticProxyTest {

    private static StaticProxyTest staticProxyTest;

    public static StaticProxyTest getInstance() {
        staticProxyTest = new StaticProxyTest();
        return staticProxyTest;
    }

    public void test() {
        //静态代理测试
        RealMovie realMovie = new RealMovie();
        Movie cinema = new StaticCinema(realMovie);
        cinema.play();

        //动态代理：电影院
        DynamicCinema dynamicCinema = new DynamicCinema(realMovie);
        Movie playing = (Movie) Proxy.newProxyInstance(RealMovie.class.getClassLoader(),
                new Class[]{Movie.class}, dynamicCinema);
        playing.play();

        //动态代理：大保健
        BingBingDaBaoJian bingBingDaBaoJian = new BingBingDaBaoJian();
        DynamicCinema dynamicCinema1 = new DynamicCinema(bingBingDaBaoJian);
        DaBaoJian daBaoJian = (DaBaoJian) Proxy.newProxyInstance(BingBingDaBaoJian.class.getClassLoader(),
                new Class[]{DaBaoJian.class}, dynamicCinema1);
        daBaoJian.anMo();

    }
}
