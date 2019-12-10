package com.example.lib_audio.mediaplayer.model;


import org.greenrobot.greendao.annotation.NotNull;

/**
 * 收藏表
 */
public class Favourite {
   
    Long favouriteId;

    String audioId;
    //一条收藏记录唯一对应一条实体

    AudioBean audioBean;

    public Favourite(Long favouriteId, @NotNull String audioId) {
        this.favouriteId = favouriteId;
        this.audioId = audioId;
    }

}
