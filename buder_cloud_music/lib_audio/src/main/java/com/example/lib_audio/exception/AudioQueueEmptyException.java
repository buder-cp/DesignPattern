package com.example.lib_audio.exception;

/**
 * 播放队列为空异常
 */
public class AudioQueueEmptyException extends RuntimeException {

    public AudioQueueEmptyException(String error) {
        super(error);
    }
}
