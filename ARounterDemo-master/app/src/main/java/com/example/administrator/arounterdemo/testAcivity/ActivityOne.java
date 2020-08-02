package com.example.administrator.arounterdemo.testAcivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.administrator.arounterdemo.ARouterConstants;
import com.example.administrator.arounterdemo.R;

@Route(path = ARouterConstants.COM_ACTIVITY1)
public class ActivityOne extends AppCompatActivity {

    private static final String TAG = "ActivityOne";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        Log.e(TAG, "onCreate");

    }
}
