package com.example.imooc_voice.view.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;


import com.example.imooc_voice.R;
import com.example.imooc_voice.api.MockData;
import com.example.imooc_voice.api.RequestCenter;
import com.example.imooc_voice.view.login.manager.UserManager;
import com.example.imooc_voice.view.login.user.LoginEvent;
import com.example.imooc_voice.view.login.user.User;
import com.example.lib_common_ui.base.BaseActivity;
import com.example.lib_network.okhttp.listener.DisposeDataListener;
import com.example.lib_network.okhttp.utils.ResponseEntityToModule;

import org.greenrobot.eventbus.EventBus;

/**
 * 登录页面
 */
public class LoginActivity extends BaseActivity implements DisposeDataListener {

    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);
        findViewById(R.id.login_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestCenter.login(LoginActivity.this);
            }
        });
    }

    @Override
    public void onSuccess(Object responseObj) {
        //处理正常逻辑
        User user = (User) responseObj;
        UserManager.getInstance().saveUser(user);
        EventBus.getDefault().post(new LoginEvent());
        finish();
    }

    @Override
    public void onFailure(Object reasonObj) {
        //登录失败逻辑
        onSuccess(ResponseEntityToModule.parseJsonToModule(
                MockData.LOGIN_DATA, User.class));
    }
}
