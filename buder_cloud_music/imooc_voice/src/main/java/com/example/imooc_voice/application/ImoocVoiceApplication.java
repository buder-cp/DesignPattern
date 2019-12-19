package com.example.imooc_voice.application;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.lib_audio.app.AudioHelper;
import com.example.lib_share.share.ShareManager;
import com.example.lib_update.app.UpdateHelper;
import com.example.lib_video.app.VideoHelper;


public class ImoocVoiceApplication extends Application {

    private static ImoocVoiceApplication mApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        //视频SDK初始化
        VideoHelper.init(this);
        //音频SDK初始化
        AudioHelper.init(this);
        //分享SDK初始化
        ShareManager.initSDK(this);
        //ARouter初始化
        ARouter.init(this);
        //更新组件下载
        UpdateHelper.init(this);
    }

    public static ImoocVoiceApplication getInstance() {
        return mApplication;
    }
}
