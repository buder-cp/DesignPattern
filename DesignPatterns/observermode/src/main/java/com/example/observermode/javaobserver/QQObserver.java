package com.example.observermode.javaobserver;

import android.util.Log;

import java.util.Observable;

public class QQObserver extends WeixinObserver {

    @Override
    public void update(Observable o, Object arg) {
        super.update(o, arg);
        Log.e("buder", "QQ received");
    }
}
