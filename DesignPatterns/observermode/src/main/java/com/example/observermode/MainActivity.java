package com.example.observermode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.observermode.javaobserver.MessagePublisher;
import com.example.observermode.javaobserver.TaobaoObserver;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        selfObserverPattern();
        javaObserverPattern();
    }

    private void javaObserverPattern() {
        TaobaoObserver observer = new TaobaoObserver();
        observer.addObservers();

        /**
         * 必须是同一个publisher对象才能顺利发送消息
         */
//        MessagePublisher publisher = new MessagePublisher();
//        publisher.notifyAllObservers();

        MessagePublisher.getInstance().notifyAllObservers();

    }

    private void selfObserverPattern() {
        ConcreteObservable concreteObservable = new ConcreteObservable();

        WeixinUser user1 = new WeixinUser("111");
        WeixinUser user2 = new WeixinUser("222");
        WeixinUser user3 = new WeixinUser("333");

        TaobaoUser taobaoUser1 = new TaobaoUser("111");
        TaobaoUser taobaoUser2 = new TaobaoUser("222");
        TaobaoUser taobaoUser3 = new TaobaoUser("333");

        concreteObservable.addObserver(user1);
        concreteObservable.addObserver(user2);
        concreteObservable.addObserver(user3);
        concreteObservable.addObserver(taobaoUser3);
        concreteObservable.addObserver(taobaoUser2);
        concreteObservable.addObserver(taobaoUser1);

        concreteObservable.notifyObservers("hehe");
    }
}
