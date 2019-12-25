package ctrip.business.event;

import android.content.Context;

/**
 * Description 每个模块分发标志到对应的页面
 * Created by Administrator
 * Time 2017/12/9  18:04
 */

public interface TaskDistribution {

    void distribution(Context context, String flag, Object... objects);
}
