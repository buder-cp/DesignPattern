package com.dn_alan.myapplication.core;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class DnMessageQueue {

    //阻塞队列
    BlockingQueue<DnMessage> blockingQueue = new ArrayBlockingQueue<>(50);

    public void enqueueMessage(DnMessage message) {
        try {
            blockingQueue.put(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //从消息队列中取出消息
    public DnMessage next() {
        try {
            return blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
