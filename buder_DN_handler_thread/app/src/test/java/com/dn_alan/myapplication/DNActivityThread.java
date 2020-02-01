package com.dn_alan.myapplication;

import com.dn_alan.myapplication.core.DnHandler;
import com.dn_alan.myapplication.core.DnLooper;
import com.dn_alan.myapplication.core.DnMessage;

import org.junit.Test;

public class DNActivityThread {

    @Test
    public void main(){

        //创建全局唯一主线程Looper对象
        DnLooper.prepare();

        //创建Handler对象
        final DnHandler dnHandler = new DnHandler(){
            @Override
            public void handleMessage(DnMessage msg) {
                super.handleMessage(msg);
                System.out.println(msg.obj.toString());
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                DnMessage message = new DnMessage();
                message.what = 1;
                message.obj = "大家好！";
                dnHandler.sendMessage(message);
            }
        }).start();

        //获取消息
        DnLooper.loop();

    }

}
