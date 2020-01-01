package com.example.facadetoagentmode;

import android.app.Application;

import com.example.facadetoagentmode.proxy.HttpProxy;
import com.example.facadetoagentmode.proxy.VolleyModel;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        HttpProxy.init(VolleyModel.getInstance(getApplicationContext()));
//        HttpProxy.init(OkHttpModel.getInstance(getApplicationContext()));
    }
}
