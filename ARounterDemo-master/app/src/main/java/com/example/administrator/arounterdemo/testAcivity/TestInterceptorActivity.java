package com.example.administrator.arounterdemo.testAcivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.administrator.arounterdemo.ARouterConstants;
import com.example.administrator.arounterdemo.R;

@Route(path = ARouterConstants.COM_ACTIVITY_INTERCEPTOR)
public class TestInterceptorActivity extends AppCompatActivity {

    @Autowired
    String extra;

    @Autowired
    int number;

    TextView mExtraView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_interceptor);

        ARouter.getInstance().inject(this);//注意添加这个注册函数

        mExtraView = findViewById(R.id.extra);


        if (!TextUtils.isEmpty(extra)) {
            mExtraView.setText(extra);
        }

//        mExtraView.setText(number + " ");

    }
}
