public class DetailEventBusManager {

    private static final String TAG = "Scn/DetailEventBusManager";
    private AlbumDetailViewImpl mAlbumDetailView;
    private DetailViewAdapter mDetailViewAdapter;
    private DetailDataManager mDetailDataManager;
    private Context mContext;

    public DetailEventBusManager(Context context, AlbumDetailViewImpl mAlbumDetailView, DetailViewAdapter mDetailViewAdapter, DetailDataManager mDetailDataManager) {
        this.mContext = context;
        this.mAlbumDetailView = mAlbumDetailView;
        this.mDetailViewAdapter = mDetailViewAdapter;
        this.mDetailDataManager = mDetailDataManager;
    }


    /**
     * 关闭加载数据dialog
     *
     * @param message
     */
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void dismissLoadingDialog(DetailEventMessage message) {
        if (null != mAlbumDetailView) {
            mAlbumDetailView.dismissLoadingDialog(message);
        }
    }

    /**
     * 视频小窗口切换
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPlayWindowToSmall(ScnPlayWindowToSmallEvent event) {
        LogUtils.d(TAG, "onPlayWindowToSmall");
        if (null != mAlbumDetailView) {
            mAlbumDetailView.onPlayWindowToSmall(event);
        }
    }

    /**
     * pingback加工数据
     *
     * @param message
     */
    @Subscribe(threadMode = ThreadMode.POSTING, priority = 999)
    public void pingbackEvent(DetailEventMessage<DetailClickPingBackBean> message) {
        if (null != mDetailDataManager) {
            mDetailDataManager.pingbackEvent(message);
        }
    }

    /**
     * 往期回顾请求加载更多数据
     *
     * @param message
     */
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void requestPastReviewDataEvent(DetailEventMessage message) {
        if (null != mDetailDataManager) {
            mDetailDataManager.requestPastReviewDataEvent(message);
        }
    }

    /**
     * 播放器起播
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onStartVideo(ScnStartVideoEvent event) {
        if (null != mDetailViewAdapter && null != event && null != event.scnEpg) {
            mDetailViewAdapter.onStartVideo(event.scnEpg);
        }
    }

    /**
     * 跳转收银台
     *
     * @param message
     */
    @Subscribe
    public void jumpCashier(DetailEventMessage message) {
        if (null != message
                && TextUtils.equals(message.getType(), DetailEventFunctionCode.SCN_DETAIL_JUMP_CASHIER)
                && null != mDetailDataManager.getEpgInfo()) {
            ModuleManagerApi.getSccnPluginApi().onJumpToPurchaseVipPage(mContext, 3, JSON.toJSONString(mDetailDataManager.getEpgInfo()));
        }
    }
}
