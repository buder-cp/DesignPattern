package com.example.administrator.arounterdemo.testService;

import android.content.Context;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.administrator.arounterdemo.ARouterConstants;

/**
 * @author xujun  on 2/7/2018.
 */
// 实现接口
@Route(path = ARouterConstants.SERVICE_HELLO, name = "test service")
public class HelloServiceImpl implements HelloService {

    private Context mContext;

    @Override
    public String sayHello(String name) {
        Toast.makeText(mContext,this.getClass().getSimpleName()+": sayHello"+" "+name,Toast.LENGTH_SHORT).show();
        return "hello, " + name;
    }

    @Override
    public void init(Context context) {
        mContext = context;
    }
}

