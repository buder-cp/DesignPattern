package com.example.observermode.javaobserver;

import android.util.Log;

import java.util.Observable;
import java.util.Observer;

public class TaobaoObserver implements Observer {

//    MessagePublisher publisher = new MessagePublisher();

    public void addObservers() {
//        publisher.addObserver(this);
//        publisher.addObserver(new WeixinObserver());


        MessagePublisher.getInstance().addObserver(this);
        MessagePublisher.getInstance().addObserver(new QQObserver());
    }

    @Override
    public void update(Observable o, Object arg) {
        Log.e("buder", "tao bao received");
    }
}
