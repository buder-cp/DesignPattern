package com.example.lib_audio.app.service;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.lib_audio.mediaplayer.core.AudioController;
import com.example.lib_base.AudioService;

/**
 * AudioService实现类
 */
@Route(path = "/audio/audio_service")
public class AudioServiceImpl implements AudioService {

    @Override
    public void pauseAudio() {
        AudioController.getInstance().pause();
    }

    @Override
    public void resumeAudio() {
        AudioController.getInstance().resume();
    }

    @Override
    public void init(Context context) {

    }
}
