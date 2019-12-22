package com.example.libbase2;

import android.app.Application;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 * create by libo
 * create on 2018/12/26
 * description 公共模块基类Application
 */
public class BaseApplication extends Application {
    public static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        init();
    }

    private void init() {
        //组件化ARouter初始化
        if (AppConfig.isDebug) {
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(this);
    }
}
