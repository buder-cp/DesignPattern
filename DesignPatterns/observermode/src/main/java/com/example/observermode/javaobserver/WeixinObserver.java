package com.example.observermode.javaobserver;

import android.util.Log;

import java.util.Observable;
import java.util.Observer;

public class WeixinObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        Log.e("buder", "wei xin received");
    }
}
