package com.example.administrator.arounterdemo.testAcivity;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.DegradeService;


/**
 * 全局降级监听
 */
@Route(path = "/hehe/1a")
public class PretreatmentServiceImpl implements DegradeService {

    @Override
    public void onLost(Context context, Postcard postcard) {
        Log.e("buder", "onLost");
    }

    @Override
    public void init(Context context) {
        Log.e("buder", "init");
    }
}
