package com.example.administrator.arounterdemo.testAcivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.administrator.arounterdemo.ARouterConstants;
import com.example.administrator.arounterdemo.R;

@Route(path = ARouterConstants.DEGRADE)
public class DegradeActivity extends Activity {

    private TextView mPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_page);
        mPath = (TextView) findViewById(R.id.error_tv);
        showPath();
    }

    private void showPath() {
        String text = "全局降级页面 class=" + this.getClass().getSimpleName();
        mPath.setText(text);
    }

}

