package com.example.hook_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hook_demo.base_reflect.Student;
import com.example.hook_demo.base_reflect.StudentTest;
import com.example.hook_demo.click_hook.ClickHookActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button hook_click = findViewById(R.id.click_hook);
        hook_click.setOnClickListener(this);

        Button reflect = findViewById(R.id.reflect);
        reflect.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.click_hook) {
            Intent intent = new Intent(this, ClickHookActivity.class);
            startActivity(intent);
        } else if (id == R.id.reflect) {
            try {
                StudentTest.reflectTest();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
