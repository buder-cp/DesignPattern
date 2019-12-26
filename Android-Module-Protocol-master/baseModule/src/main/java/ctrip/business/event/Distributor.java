package ctrip.business.event;

import android.content.Context;

import java.util.HashMap;

/**
 * Description  用于获取模块转发器的类名
 */

public class Distributor {
    private static HashMap<String, String> hashMap = new HashMap<>();
    private static TaskDistribution taskDistribution;

    public static void init() {
        hashMap.put("m1", "ctrip.module1.Taskimp1");
        hashMap.put("m2", "ctrip.module2.Taskimp2");
    }

    private static void getTaskDistribution(String flag) {
        try {
            Class c = null;
            if (flag != null && flag.startsWith("module1")) {
                c = Class.forName(hashMap.get("m1"));
            }
            if (flag != null && flag.startsWith("module2")) {
                c = Class.forName(hashMap.get("m2"));
            }
            taskDistribution = (TaskDistribution) c.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static void turn2Acitivity(Context context, String flag, Object... objects) {
        getTaskDistribution(flag);
        taskDistribution.distribution(context, flag, objects);
    }
}
