package com.example.hook_demo.click_hook;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hook_demo.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClickHookActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_hook);

        Button btn = findViewById(R.id.text_hook_click);
        btn.setOnClickListener(this);

        try {
            hookOnClickListener(btn);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.text_hook_click) {
            Toast.makeText(this, "原始点击事件", Toast.LENGTH_SHORT).show();
        }
    }


    //android.view.View.ListenerInfo
    //android.view.View.ListenerInfo#mOnClickListener
    //android.view.View.OnClickListener.onClick

    public void hookOnClickListener(View view) throws Exception {

        //获取 ListenerInfo 对象
        Method getListenerInfo = View.class.getDeclaredMethod("getListenerInfo");
        getListenerInfo.setAccessible(true);
        Object listenerInfo = getListenerInfo.invoke(view);

        //得到原始的 OnClickListener事件方法
        Class<?> listenerInfoClz = Class.forName("android.view.View$ListenerInfo");
        Field mOnClickListener = listenerInfoClz.getDeclaredField("mOnClickListener");
        mOnClickListener.setAccessible(true);
        View.OnClickListener originOnClickListener = (View.OnClickListener) mOnClickListener.get(listenerInfo);

        //用Hook代理类 替换原始的 OnClickListener
        View.OnClickListener hookedOnCliclListener = new HookProxyClass(originOnClickListener);
        mOnClickListener.set(listenerInfo, hookedOnCliclListener);

    }


    class HookProxyClass implements View.OnClickListener {

        private View.OnClickListener origin;

        HookProxyClass(View.OnClickListener origin) {
            this.origin = origin;
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(ClickHookActivity.this, "搞点自己的事情", Toast.LENGTH_SHORT).show();

            if (origin != null) {
                origin.onClick(v);
            }

        }
    }

}


