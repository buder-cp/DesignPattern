package com.example.imooc_voice.view.friend.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.imooc_voice.R;
import com.example.imooc_voice.model.friend.FriendBodyValue;
import com.example.imooc_voice.utils.UserManager;
import com.example.imooc_voice.view.login.LoginActivity;
import com.example.lib_audio.app.AudioHelper;
import com.example.lib_common_ui.MultiImageViewLayout;
import com.example.lib_common_ui.recyclerview.MultiItemTypeAdapter;
import com.example.lib_common_ui.recyclerview.base.ItemViewDelegate;
import com.example.lib_common_ui.recyclerview.base.ViewHolder;
import com.example.lib_image_loader.app.ImageLoaderManager;
import com.example.lib_video.videoplayer.core.VideoAdContext;

import java.util.List;

public class FriendRecyclerAdapter extends MultiItemTypeAdapter {

    public static final int MUSIC_TYPE = 0x01; //音乐类型
    public static final int VIDEO_TYPE = 0x02; //视频类型

    private Context mContext;

    public FriendRecyclerAdapter(Context context, List<FriendBodyValue> datas) {
        super(context, datas);
        mContext = context;
        addItemViewDelegate(MUSIC_TYPE, new MusicItemDelegate());
        addItemViewDelegate(VIDEO_TYPE, new VideoItemDelegate());
    }

    /**
     * 图片类型item
     */
    private class MusicItemDelegate implements ItemViewDelegate<FriendBodyValue> {
        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_friend_list_picture_layout;
        }

        @Override
        public boolean isForViewType(FriendBodyValue item, int position) {
            return item.type == FriendRecyclerAdapter.MUSIC_TYPE;
        }

        @Override
        public void convert(ViewHolder holder, final FriendBodyValue recommandBodyValue, int position) {
            holder.setText(R.id.name_view, recommandBodyValue.name + " 分享单曲:");
            holder.setText(R.id.fansi_view, recommandBodyValue.fans + "粉丝");
            holder.setText(R.id.text_view, recommandBodyValue.text);
            holder.setText(R.id.zan_view, recommandBodyValue.zan);
            holder.setText(R.id.message_view, recommandBodyValue.msg);
            holder.setText(R.id.audio_name_view, recommandBodyValue.audioBean.name);
            holder.setText(R.id.audio_author_view, recommandBodyValue.audioBean.album);
            holder.setOnClickListener(R.id.album_layout, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //调用播放器装饰类
                    AudioHelper.addAudio((Activity) mContext, recommandBodyValue.audioBean);
                }
            });
            holder.setOnClickListener(R.id.guanzhu_view, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!UserManager.getInstance().hasLogined()) {
                        //goto login
                        LoginActivity.start(mContext);
                    }
                }
            });
            ImageView avatar = holder.getView(R.id.photo_view);
            ImageLoaderManager.getInstance().displayImageForCircle(avatar, recommandBodyValue.avatr);
            ImageView albumPicView = holder.getView(R.id.album_view);
            ImageLoaderManager.getInstance()
                    .displayImageForView(albumPicView, recommandBodyValue.audioBean.albumPic);

            MultiImageViewLayout imageViewLayout = holder.getView(R.id.image_layout);
            imageViewLayout.setList(recommandBodyValue.pics);
        }
    }

    /**
     * 视频类型item
     */
    private class VideoItemDelegate implements ItemViewDelegate<FriendBodyValue> {

        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_friend_list_video_layout;
        }

        @Override
        public boolean isForViewType(FriendBodyValue item, int position) {
            return item.type == FriendRecyclerAdapter.VIDEO_TYPE;
        }

        @Override
        public void convert(ViewHolder holder, FriendBodyValue recommandBodyValue, int position) {
            RelativeLayout videoGroup = holder.getView(R.id.video_layout);
            VideoAdContext mAdsdkContext = new VideoAdContext(videoGroup, recommandBodyValue.videoUrl);
            holder.setText(R.id.fansi_view, recommandBodyValue.fans + "粉丝");
            holder.setText(R.id.name_view, recommandBodyValue.name + " 分享视频");
            holder.setText(R.id.text_view, recommandBodyValue.text);
            holder.setOnClickListener(R.id.guanzhu_view, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!UserManager.getInstance().hasLogined()) {
                        //goto login
                        LoginActivity.start(mContext);
                    }
                }
            });
            ImageView avatar = holder.getView(R.id.photo_view);
            ImageLoaderManager.getInstance().displayImageForCircle(avatar, recommandBodyValue.avatr);
        }
    }
}



