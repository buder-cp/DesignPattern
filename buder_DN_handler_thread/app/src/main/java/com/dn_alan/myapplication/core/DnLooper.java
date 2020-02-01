package com.dn_alan.myapplication.core;

import android.os.Looper;
import android.os.MessageQueue;

public class DnLooper {
    static final ThreadLocal<DnLooper> sThreadLocal = new ThreadLocal<DnLooper>();
    final DnMessageQueue mQueue;

    private DnLooper(){
        mQueue = new DnMessageQueue();
    }

    public static void prepare() {
        if (sThreadLocal.get() != null) {
            throw new RuntimeException("Only one DnLooper may be created per thread");
        }
        sThreadLocal.set(new DnLooper());
    }

    public static DnLooper myLooper() {
        return sThreadLocal.get();
    }

    public static void loop() {
        //从全局ThreadLocalMap中获取唯一， looper对象
        DnLooper dnLooper = myLooper();
        DnMessageQueue mQueue = dnLooper.mQueue;

        while (true){
            DnMessage message = mQueue.next();
            if(message != null ){
                message.target.dispatchMessage(message);
            }
        }
    }
}
