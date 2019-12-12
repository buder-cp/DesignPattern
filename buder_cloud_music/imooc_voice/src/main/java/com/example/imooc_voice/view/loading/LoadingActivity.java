package com.example.imooc_voice.view.loading;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.imooc_voice.R;
import com.example.imooc_voice.view.home.HomeActivity;
import com.example.lib_common_ui.base.BaseActivity;
import com.example.lib_common_ui.base.constant.Constant;

public class LoadingActivity extends BaseActivity {

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            startActivity(new Intent(LoadingActivity.this, HomeActivity.class));
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_layout);
        if (hasPermission(Constant.WRITE_READ_EXTERNAL_PERMISSION)) {
            doSDCardPermission();
        } else {
            requestPermission(Constant.WRITE_READ_EXTERNAL_CODE, Constant.WRITE_READ_EXTERNAL_PERMISSION);
        }
    }


    @Override
    public void doSDCardPermission() {
        mHandler.sendEmptyMessageDelayed(0, 3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }
}
