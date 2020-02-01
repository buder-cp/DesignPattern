package com.dn_alan.myapplication.core;

import android.os.Handler;

public class DnMessage {
    //消息标志
    public int what;
    //消息内容
    public Object obj;
    //Handler对象
    public DnHandler target;
}


