
public class ScnPlayDataDispatcher {
    private static final String TAG = "Scn/ScnPlayDataDispatcher";

    //----------------播放器接收的事件 start-->

    /**
     * 专辑信息，播放器起播使用，调用epgInfo成功后设置
     */
    public static void onGetAlbumDataSuccess(ScnEpg data) {
        if (null == data) {
            LogUtils.e(TAG, "onGetAlbumDataSuccess data is null");
        } else {
            LogUtils.d(TAG, "onGetAlbumDataSuccess album name = ", data.getAlbumTitle());
            notifySticky(new ScnGetAlbumSuccessEvent(data));
        }
    }

    /**
     * 播放的节目内容，包括剧集类和来源类，单片不需要，调用episodeList成功后设置
     * 分页加载时，每一页加载成功后都需要调用
     * 注意不要重复调用
     */
    public static void addData(List<ScnEpg> dataList) {
        if (ListUtils.isEmpty(dataList)) {
            LogUtils.e(TAG, "addData dataList is empty!");
        } else {
            LogUtils.d(TAG, "addData dataList.size=" + dataList.size());
            notifySticky(new ScnLoadMoreVideosSuccessEvent(dataList));
        }
    }

    /**
     * 详情页切换视频，包括点击剧集列表、往期回顾
     *
     * @param targetVideo
     */
    public static void switchVideo(ScnEpg targetVideo) {
        if (null == targetVideo) {
            LogUtils.e(TAG, "switchVideo: targetVideo is null!");
        } else {
            LogUtils.i(TAG, "switchVideo, video name =" + targetVideo.getVideoTitle());
            notify(new ScnSwitchVideoEvent(targetVideo));
        }
    }

    //----------------播放器接收的事件 end-->


    //----------------播放器发出的事件 start-->

    /**
     * 开始播放视频
     *
     * @param playData
     */
    public static void onStartVideo(ScnEpg playData) {
        if (null == playData) {
            LogUtils.e(TAG, "onStartVideo: playData is null");
        } else {
            LogUtils.i(TAG, "onStartVideo, video   name =" + playData.getVideoTitle());
            notify(new ScnStartVideoEvent(playData));
        }
    }

    /**
     * 切换到小窗口
     */
    public static void onPlayWindowToSmall() {
        LogUtils.i(TAG, "onPlayWindowToSmall");
        notify(new ScnPlayWindowToSmallEvent());
    }

    /**
     * menu浮层切换节目
     */
    public static void switchVideoByPlayerMenu(ScnEpg targetVideo) {
        LogUtils.i(TAG, "switchVideoByPlayerMenu");
        if (null == targetVideo) {
            LogUtils.e(TAG, "switchVideoByPlayerMenu: targetVideo is null!");
        } else {
            LogUtils.i(TAG, "switchVideoByPlayerMenu, video name =" + targetVideo.getVideoTitle());
            notify(new SwitchVideoByPlayerMenuEvent(targetVideo));
        }
    }

    /**
     * menu浮层切换清晰度
     */
    public static void switchSharpnessByPlayerMenu(ScnPlaySharpnessData targetSharpness) {
        if (null == targetSharpness) {
            LogUtils.e(TAG, "switchSharpnessByPlayerMenu: targetSharpness is null!");
        } else {
            LogUtils.i(TAG, "switchSharpnessByPlayerMenu, targetSharpness name =",targetSharpness.toString());
            notify(new SwitchSharpnessByPlayerMenuEvent(targetSharpness));
        }
    }

    //----------------播放器发出的事件 end-->
    private static void notify(Object event) {
        EventBus.getDefault().post(event);
    }

    private static void notifySticky(Object event) {
        EventBus.getDefault().postSticky(event);
    }

}
