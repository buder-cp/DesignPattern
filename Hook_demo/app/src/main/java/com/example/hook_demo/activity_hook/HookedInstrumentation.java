package com.example.hook_demo.activity_hook;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class HookedInstrumentation extends Instrumentation {

    private Instrumentation origin;

    public HookedInstrumentation(Instrumentation base) {
        this.origin = base;
    }

    public ActivityResult execStartActivity(
            Context who, IBinder contextThread, IBinder token, Activity target,
            Intent intent, int requestCode, Bundle options) {

        Log.d("log", "Hook拦截了" + who + "执行的startActivity。跳转到页面" + target);
        Toast.makeText(who, "hahaha", Toast.LENGTH_SHORT).show();

        try {

            Method execStartActivity = Instrumentation.class.getDeclaredMethod(
                    "execStartActivity", Context.class, IBinder.class,
                    IBinder.class, Activity.class,
                    Intent.class, int.class, Bundle.class);
            execStartActivity.setAccessible(true);

            return (ActivityResult) execStartActivity.invoke(origin, who,
                    contextThread, token, target, intent, requestCode, options);

        } catch (Exception e) {
            Log.d("log", "某该死的rom修改了  需要手动适配 ");
            e.printStackTrace();
            throw new RuntimeException("do not support please adapt it");
        }

    }


    public static void hookContextStartActivity() throws Exception {

        // 获取当前 ActivityThread 对象
        Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
        Method currentActivityThreadMethod = activityThreadClass.getDeclaredMethod("currentActivityThread");
        currentActivityThreadMethod.setAccessible(true);
        Object currentActivityThread = currentActivityThreadMethod.invoke(null);

        // 获取原始 mInstrumentation 字段
        Field mInstrumentationField = activityThreadClass.getDeclaredField("mInstrumentation");
        mInstrumentationField.setAccessible(true);
        Instrumentation mInstrumentation = (Instrumentation) mInstrumentationField.get(currentActivityThread);

        // 创建代理对象
        Instrumentation evilInstrumentation = new HookedInstrumentation(mInstrumentation);

        // 偷梁换柱
        mInstrumentationField.set(currentActivityThread, evilInstrumentation);

    }


}
