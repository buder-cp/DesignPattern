package com.example.administrator.arounterdemo.testAcivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.administrator.arounterdemo.ARouterConstants;


@Route(path = ARouterConstants.DEGRADE)
public class DegradeActivity extends Activity {


    private TextView mPath;
    private TextView mParams;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(com.alibaba.android.router.module.R.layout.activity_modul);
//        mPath = (TextView) findViewById(com.alibaba.android.router.module.R.id.path);
//        mParams = (TextView) findViewById(com.alibaba.android.router.module.R.id.params);
        showPath();
        showParams();
    }


    private void showParams() {
//        String path=getIntent().getStringExtra(DefaultDegrade.KEY_PATH);
//        mParams.setText("被降级postcard path="+path);
    }

    private void showPath() {
        String text = "全局降级页面 class=" + this.getClass().getSimpleName();
        mPath.setText(text);
    }

}

