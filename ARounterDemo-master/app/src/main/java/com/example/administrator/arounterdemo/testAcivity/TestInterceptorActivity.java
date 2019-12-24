package com.example.administrator.arounterdemo.testAcivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.administrator.arounterdemo.ARouterConstants;
import com.example.administrator.arounterdemo.R;

@Route(path = ARouterConstants.COM_ACTIVITY_INTERCEPTOR)
public class TestInterceptorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_interceptor);
    }
}
