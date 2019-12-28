package com.example.designpattern;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.designpattern.decoration.BiShangLiangShanService;
import com.example.designpattern.decoration.BingBingMiss;
import com.example.designpattern.decoration.LipsAndLipsService;
import com.example.designpattern.proxy.StaticProxyTest;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_proxy_static = findViewById(R.id.proxy_static_mode);
        btn_proxy_static.setOnClickListener(this);

        Button btn_decoration_mode = findViewById(R.id.decoration_mode);
        btn_decoration_mode.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.proxy_static_mode) {
            StaticProxyTest.getInstance().test();
        } else if (id == R.id.decoration_mode) {

            BingBingMiss bingMiss = new BingBingMiss();
            LipsAndLipsService lipsService = new LipsAndLipsService(bingMiss);
            lipsService.lipsAndLipsService();
            BiShangLiangShanService biShanService = new BiShangLiangShanService(bingMiss);
            biShanService.biShangLiangShanService();

        }
    }
}
