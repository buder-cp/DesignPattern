package com.example.apple.myrouterproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.libbase2.RouterPath;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void routeWithArgs(View view) {
        ARouter.getInstance().build(RouterPath.ROUTER_LOGIN)
                .withString("phone", "18201038278")
                .withTransition(R.anim.dialog_enter, R.anim.dialog_exit)
                .navigation();
    }
}
