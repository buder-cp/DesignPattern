package com.example.administrator.arounterdemo;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @author xujun  on 2/7/2018.
 */
public class App extends Application {

    static App mApp;

    public static App getApp() {
        return mApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApp=this;
        ARouter.openLog();     // 打印日志
        ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        ARouter.init( this ); // 尽可能早，推荐在Application中初始化
    }
}
