package com.dn_alan.myapplication.core;

import android.os.Message;

public class DnHandler {

    private DnLooper dnLooper;
    private DnMessageQueue dnMessageQueue;

    public DnHandler() {
        dnLooper = DnLooper.myLooper();
        if (dnLooper == null) {
            throw new RuntimeException(
                    "Can't create handler inside thread " + Thread.currentThread()
                            + " that has not called Looper.prepare()");
        }
        dnMessageQueue = dnLooper.mQueue;
    }

    public void handleMessage(DnMessage msg) {
    }

    public void sendMessage(DnMessage message) {
        //将消息放入消息队列
        enqueueMessage(message);
    }

    private void enqueueMessage(DnMessage message) {
        //赋值当前消息
        message.target = this;

        //使用dnMessageQueue，将消息传入
        dnMessageQueue.enqueueMessage(message);
    }

    public void dispatchMessage(DnMessage message) {
        handleMessage(message);
    }
}
