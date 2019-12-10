package com.example.lib_audio.mediaplayer.core;

import android.media.MediaPlayer;

import java.io.IOException;

/**
 * 带状态的MediaPlayer
 */
public class CustomMediaPlayer extends MediaPlayer implements MediaPlayer.OnCompletionListener {

    public enum Status {
        IDEL, INITALIZED, STARTED, PAUSED, STOPPTED, COMPLETED
    }

    private OnCompletionListener mCompletionListener;
    private Status mState;

    public CustomMediaPlayer() {
        super();
        mState = Status.IDEL;
        super.setOnCompletionListener(this);
    }


    @Override
    public void reset() {
        super.reset();
        mState = Status.IDEL;
    }

    @Override
    public void setDataSource(String path) throws IOException, IllegalArgumentException, IllegalStateException, SecurityException {
        super.setDataSource(path);
        mState = Status.INITALIZED;
    }

    @Override
    public void start() throws IllegalStateException {
        super.start();
        mState = Status.STARTED;
    }

    @Override
    public void pause() throws IllegalStateException {
        super.pause();
        mState = Status.PAUSED;
    }

    @Override
    public void stop() throws IllegalStateException {
        super.stop();
        mState = Status.STOPPTED;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        mState = Status.COMPLETED;
    }

    public Status getState() {
        return mState;
    }

    public boolean isComplete() {
        return mState == Status.COMPLETED;
    }

    public void setCompleteListener(OnCompletionListener listener) {
        mCompletionListener = listener;
    }
}
