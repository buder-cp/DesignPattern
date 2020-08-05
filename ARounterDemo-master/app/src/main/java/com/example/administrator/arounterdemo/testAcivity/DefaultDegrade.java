package com.example.administrator.arounterdemo.testAcivity;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.DegradeService;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.administrator.arounterdemo.ARouterConstants;


/**
 * 全局降级监听，当找不到目标页面时，会调用这里
 */
@Route(path = "/hehe/1a")
public class DefaultDegrade implements DegradeService {

    @Override
    public void onLost(Context context, Postcard postcard) {
        Log.e("buder", "onLost");
        if (context instanceof Activity) {
            Toast.makeText(context, "未找到目标页面 path=" + postcard.getPath() +
                    " group=" + postcard.getGroup() + " 做降级处理，5s后跳转降级页", Toast.LENGTH_LONG).show();
        }
        jumpDegradePage(context, postcard);
    }

    @Override
    public void init(Context context) {
        Log.e("buder", "init");
        //跳转目标activity未找到时，创建此降级服务实例，懒加载
    }

    private void jumpDegradePage(final Context context, final Postcard postcard) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ARouter.getInstance().build(ARouterConstants.DEGRADE)
                        .navigation(context);
            }
        }, 5 * 1000);
    }

}
