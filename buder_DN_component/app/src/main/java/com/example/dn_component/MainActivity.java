package com.example.dn_component;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.router_annotation.Route;

@Route(path = "/main/hehe")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
