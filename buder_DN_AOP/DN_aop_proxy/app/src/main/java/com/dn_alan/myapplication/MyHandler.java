package com.dn_alan.myapplication;

import android.content.Context;
import android.content.Intent;

import com.dn_alan.myapplication.util.SharePreferenceUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyHandler implements InvocationHandler {
    private Object target;
    private Context context;

    public MyHandler(Context context) {
        this.target = context;
        this.context = context;
    }

    /**
     * 这个invoke就是拦截Object对象的所有方法
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        if (SharePreferenceUtil.getBooleanSp(SharePreferenceUtil.IS_LOGIN, context)) {
            result = method.invoke(target, args);
        } else {
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
        }
        return result;
    }
}
