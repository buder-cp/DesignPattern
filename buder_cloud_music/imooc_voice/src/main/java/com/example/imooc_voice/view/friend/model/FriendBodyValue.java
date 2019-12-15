package com.example.imooc_voice.view.friend.model;

import com.example.imooc_voice.model.BaseModel;
import com.example.lib_audio.mediaplayer.model.AudioBean;

import java.util.ArrayList;

/**
 * 朋友数据实体
 */
public class FriendBodyValue extends BaseModel {

    public int type;
    public String avatr;
    public String name;
    public String fans;
    public String text;
    public ArrayList<String> pics;
    public String videoUrl;
    public String zan;
    public String msg;
    public AudioBean audioBean;
}
