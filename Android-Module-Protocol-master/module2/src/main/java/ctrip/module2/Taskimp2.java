package ctrip.module2;

import android.content.Context;
import android.content.Intent;

import ctrip.business.event.TaskDistribution;

/**
 * Description
 * Created by Administrator
 * Time 2017/12/9  18:13
 */

public class Taskimp2 implements TaskDistribution {
    @Override
    public void distribution(Context context, String flag, Object... objects) {
        if(flag.endsWith("pay")){
            Intent intent = new Intent(context, ModuleTwoActivity.class);
            context.startActivity(intent);
        }
    }
}
