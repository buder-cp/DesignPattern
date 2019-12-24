package com.example.administrator.arounterdemo.testAcivity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.administrator.arounterdemo.R;

public class UrlSchemeActivity extends AppCompatActivity {

    private static final String TAG = "UrlSchemeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url);
        //        直接通过ARouter处理外部Uri
        final Uri uri = getIntent().getData();
        Log.i(TAG, "onCreate: uri=" + uri);
        ARouter.getInstance().build(uri).navigation(this, new NavCallback() {
            @Override
            public void onArrival(Postcard postcard) {
                finish();
            }

            @Override
            public void onLost(Postcard postcard) {
                super.onLost(postcard);
                Log.i(TAG, "onLost: uri=" + uri);
                //                Toast.makeText(UrlSchemeActivity.this,String.format("找不到可以处理该
                // URI %s 的 Activity",uri),Toast.LENGTH_SHORT).show();
                // 找不到的时候 finish 掉当前 activity
                finish();
            }
        });

    }
}
