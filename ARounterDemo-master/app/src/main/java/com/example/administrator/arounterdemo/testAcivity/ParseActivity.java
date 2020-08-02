package com.example.administrator.arounterdemo.testAcivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.administrator.arounterdemo.ARouterConstants;
import com.example.administrator.arounterdemo.R;
import com.example.administrator.arounterdemo.entity.Person;
import com.example.administrator.arounterdemo.entity.TestObj;

@Route(path = ARouterConstants.COM_PARSE_ACTIVITY)
public class ParseActivity extends AppCompatActivity {

    private static final String TAG = "ParseActivity";


    @Autowired
    String name;

    @Autowired
    int age;

    @Autowired()
    Person person;

    @Autowired
    TestObj mTestObj;

    @Autowired // 注意字段的名称必须与 withObject 的 key 一致
    TestObj testObj;
    private android.widget.TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parse);
        this.tv = (TextView) findViewById(R.id.tv);
        ARouter.getInstance().inject(this);//注意添加这个注册函数

        Log.i(TAG, "onCreate: name=" +name+" age="+age+" person"+person+" testObj="+testObj+" mTestObj="+mTestObj);
        tv.setText(name+"\n"+age+"\n"+person);

        Intent intent = getIntent();
        if(intent!=null){
            String lName = intent.getStringExtra(MainActivity.NAME);
            int lAge = intent.getIntExtra(MainActivity.AGE,0);
            Person lPerson=intent.getParcelableExtra(MainActivity.PERSON);
            Log.i(TAG, "onCreate: lName=" +lName+" lAge="+lAge+" lPerson="+lPerson+"\n"+intent.getExtras().toString());
        }
    }
}
