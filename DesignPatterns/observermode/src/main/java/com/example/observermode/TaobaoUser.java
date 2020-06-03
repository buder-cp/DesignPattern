package com.example.observermode;

import android.util.Log;

public class TaobaoUser implements MyObserver {

    private String name;

    public TaobaoUser(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        Log.e("buder", message);
    }
}
