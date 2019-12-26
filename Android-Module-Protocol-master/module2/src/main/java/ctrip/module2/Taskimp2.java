package ctrip.module2;

import android.content.Context;
import android.content.Intent;

import ctrip.business.event.TaskDistribution;


public class Taskimp2 implements TaskDistribution {
    @Override
    public void distribution(Context context, String flag, Object... objects) {
        if(flag.endsWith("pay")){
            Intent intent = new Intent(context, ModuleTwoActivity.class);
            context.startActivity(intent);
        }
    }
}
