package com.example.lib_audio.app;

import android.content.Context;

import com.example.lib_audio.mediaplayer.db.GreenDaoHelper;

/**
 * @function 唯一与外界通信的帮助类
 */
public final class AudioHelper {

    //SDK全局Context, 供子模块用
    private static Context mContext;

    public static void init(Context context) {
        mContext = context;
        GreenDaoHelper.initDatabase();
    }


    public static Context getContext() {
        return mContext;
    }
}
