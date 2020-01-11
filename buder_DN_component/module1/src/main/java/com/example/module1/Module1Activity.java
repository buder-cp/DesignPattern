package com.example.module1;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.example.router_annotation.Extra;
import com.example.router_annotation.Route;


@Route(path = "/module1/test")
public class Module1Activity extends Activity {

    @Extra
    String msg;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module1);


    }
}
