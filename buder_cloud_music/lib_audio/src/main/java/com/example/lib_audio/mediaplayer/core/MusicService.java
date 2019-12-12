package com.example.lib_audio.mediaplayer.core;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;


import com.example.lib_audio.app.AudioHelper;
import com.example.lib_audio.mediaplayer.model.AudioBean;
import com.example.lib_audio.mediaplayer.view.NotificationHelper;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;


/**
 * 音乐后台服务,并更新notification状态
 */
public class MusicService extends Service implements NotificationHelper.NotificationHelperListener {

    private static String DATA_AUDIOS = "AUDIOS";
    //actions
    private static String ACTION_START = "ACTION_START";

    private ArrayList<AudioBean> mAudioBeans;

    private NotificationReceiver mReceiver;

    /**
     * 外部直接service方法
     */
    public static void startMusicService(ArrayList<AudioBean> audioBeans) {
        Intent intent = new Intent(AudioHelper.getContext(), MusicService.class);
        intent.setAction(ACTION_START);
        //还需要传list数据进来
        intent.putExtra(DATA_AUDIOS, audioBeans);
        AudioHelper.getContext().startService(intent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);
        registerBroadcastReceiver();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mAudioBeans = (ArrayList<AudioBean>) intent.getSerializableExtra(DATA_AUDIOS);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        unRegisterBroadcastReceiver();
    }

    private void registerBroadcastReceiver() {
        if (mReceiver == null) {
            mReceiver = new NotificationReceiver();
            IntentFilter filter = new IntentFilter();
            filter.addAction(NotificationReceiver.ACTION_STATUS_BAR);
            registerReceiver(mReceiver, filter);
        }
    }

    private void unRegisterBroadcastReceiver() {
        if (mReceiver != null) {
            unregisterReceiver(mReceiver);
        }
    }

    @Override
    public void onNotificationInit() {
    }

    /**
     * 接收Notification发送的广播
     */
    public static class NotificationReceiver extends BroadcastReceiver {
        public static final String ACTION_STATUS_BAR =
                AudioHelper.getContext().getPackageName() + ".NOTIFICATION_ACTIONS";
        public static final String EXTRA = "extra";
        public static final String EXTRA_PLAY = "play_pause";
        public static final String EXTRA_NEXT = "play_next";
        public static final String EXTRA_PRE = "play_previous";
        public static final String EXTRA_FAV = "play_favourite";

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent == null || TextUtils.isEmpty(intent.getAction())) {
                return;
            }
        }
    }
}
