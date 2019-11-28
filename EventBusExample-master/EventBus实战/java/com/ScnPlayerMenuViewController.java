        case CONTENT_TYPE_PLAYING_EPISODE://选集
        if (data instanceof ScnPlayData) {
        hide();
        sendScnVedioClickPingBack(mVideoType, index + 1, mVideoId);
        ScnPlayDataDispatcher.switchVideoByPlayerMenu(((ScnPlayData) data).getData());
        }
        break;
        case CONTENT_TYPE_PLAYING_SHARPNESS://清晰度
        if (data instanceof ScnPlaySharpnessData) {
        PlayerClickPingBackHelper.sendQualityClickPingBack(((ScnPlaySharpnessData) data).getSharpnessName(), mVideoType, mVideoId);
        hide();
        LogUtils.d(TAG, "menu 点击切换清晰度操作", ((ScnPlaySharpnessData) data).toString());
        ScnPlayDataDispatcher.switchSharpnessByPlayerMenu((ScnPlaySharpnessData) data);
        ScnPlayPingbackHelper.sendChangeFromraPingBack(((ScnPlaySharpnessData) data).Bit_Rate, mBitRate);
        }