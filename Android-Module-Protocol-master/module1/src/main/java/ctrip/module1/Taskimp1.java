package ctrip.module1;


import android.content.Context;
import android.content.Intent;

import ctrip.business.event.TaskDistribution;


public class Taskimp1 implements TaskDistribution{

    @Override
    public void distribution(Context context, String flag, Object... objects) {
        if(flag.endsWith("login")){
            //跳转到登录页面
            //context.startActivity(...);
        }

        if(flag.endsWith("login")){
            Intent intent = new Intent(context, ModuleOneActivity.class);
            context.startActivity(intent);
        }
    }
}
