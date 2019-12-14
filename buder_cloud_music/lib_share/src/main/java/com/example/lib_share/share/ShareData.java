package com.example.lib_share.share;


import cn.sharesdk.framework.Platform;

/**
 * @author 要分享的数据实体
 */
public class ShareData {

    /**
     * 要分享到的平台
     */
    public ShareManager.PlatformType mPlatformType;

    /**
     * 要分享到的平台的参数
     */
    public Platform.ShareParams mShareParams;
}
