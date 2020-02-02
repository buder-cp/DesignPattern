package com.dn_alan.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.lang.reflect.Proxy;

public class MainActivity extends AppCompatActivity implements ILogin {
    //接口的应用
    private ILogin proxyLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 第一个参数：类加载器
         * 第二个参数：代理对象的目标类
         * 第三个参数：回调处理类
         */
        proxyLogin = (ILogin) Proxy.newProxyInstance(
                this.getClassLoader(), new Class[]{ILogin.class}, new MyHandler(this));
    }

    public void me(View view) {
        proxyLogin.toLogin();
    }

    public void play(View view) {
        proxyLogin.toLogin();
    }

    @Override
    public void toLogin() {
        Intent intent = new Intent(
                MainActivity.this, MenberAcitivity.class);
        startActivity(intent);
    }

    public void look(View view) {
    }
}
