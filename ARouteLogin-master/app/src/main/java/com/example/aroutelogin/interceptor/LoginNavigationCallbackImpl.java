package com.example.aroutelogin.interceptor;

import android.os.Bundle;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.example.aroutelogin.interfaces.RoutePath;


public class LoginNavigationCallbackImpl  implements NavigationCallback {
    @Override //找到了
    public void onFound(Postcard postcard) {
        Log.e("buder", "onFound");
    }

    @Override //找不到了
    public void onLost(Postcard postcard) {
        Log.e("buder", "onLost");
    }

    @Override    //跳转成功了
    public void onArrival(Postcard postcard) {
        Log.e("buder", "onArrival");
    }

    @Override
    public void onInterrupt(Postcard postcard) {
        Log.e("buder", "onInterrupt");
        String path = postcard.getPath();
        LogUtils.v(path);
        Bundle bundle = postcard.getExtras();
        // 被登录拦截了下来了
        // 需要调转到登录页面，把参数跟被登录拦截下来的路径传递给登录页面，登录成功后再进行跳转被拦截的页面
        ARouter.getInstance().build(RoutePath.LOGIN_PATH)
                .with(bundle)
                .withString(RoutePath.PATH, path)
                .navigation();
    }
}
