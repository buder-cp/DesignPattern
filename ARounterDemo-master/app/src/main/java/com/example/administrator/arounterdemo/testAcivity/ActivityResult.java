package com.example.administrator.arounterdemo.testAcivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.administrator.arounterdemo.ARouterConstants;
import com.example.administrator.arounterdemo.R;

@Route(path = ARouterConstants.COM_ACTIVITY_RESULT)
public class ActivityResult extends AppCompatActivity {

    public static final String RESULT = "result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
    }

    public void onButtonClick(View view){
        Intent data = new Intent();
        data.putExtra(RESULT,"success");
        setResult(Activity.RESULT_OK, data);
        finish();
    }
}
