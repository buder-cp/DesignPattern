package com.dn_alan.myapplication;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.dn_alan.myapplication.annotation.BehaviorTrace;
import com.dn_alan.myapplication.annotation.UserInfoBehaviorTrace;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //摇一摇
    @UserInfoBehaviorTrace("摇一摇")
    @BehaviorTrace("摇一摇")
    public void mShake(View view) {
        long begin = System.currentTimeMillis();
        SystemClock.sleep(new Random().nextInt(2000));
        long duration = System.currentTimeMillis() - begin;
    }

    //语音消息
    @BehaviorTrace("语音消息")
    public void mAudio(View view) {

    }

    //视频通话
    @BehaviorTrace("视频通话")
    public void mVideo(View view) {
    }

    //发表说说
    @BehaviorTrace("发表说说")
    public void saySomething(View view) {
    }
}
