package com.example.imooc_voice.application;

import android.app.Application;

import com.example.lib_audio.app.AudioHelper;
import com.example.lib_share.share.ShareManager;
import com.example.lib_update.app.UpdateHelper;


public class ImoocVoiceApplication extends Application {

    private static ImoocVoiceApplication mApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        //音频SDK初始化
        AudioHelper.init(this);
        //分享组件初始化
        ShareManager.initSDK(this);
        //更新组件初始化
        UpdateHelper.init(this);

    }

    public static ImoocVoiceApplication getInstance() {
        return mApplication;
    }
}