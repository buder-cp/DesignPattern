package com.example.imooc_voice.api;

import com.example.imooc_voice.model.discory.BaseRecommandModel;
import com.example.imooc_voice.model.discory.BaseRecommandMoreModel;
import com.example.imooc_voice.model.friend.BaseFriendModel;
import com.example.imooc_voice.model.user.User;
import com.example.lib_network.okhttp.CommonOkHttpClient;
import com.example.lib_network.okhttp.request.CommonRequest;
import com.example.lib_network.okhttp.request.RequestParams;
import com.example.lib_network.okhttp.listener.DisposeDataHandle;
import com.example.lib_network.okhttp.listener.DisposeDataListener;

/**
 * 请求中心
 */
public class RequestCenter {

    static class HttpConstants {
        private static final String ROOT_URL = "http://imooc.com/api";

        /**
         * 首页请求接口
         */
        private static String HOME_RECOMMAND = ROOT_URL + "/product/home_recommand.php";

        private static String HOME_FRIEND = ROOT_URL + "/product/home_friend.php";

        private static String HOME_RECOMMAND_MORE = ROOT_URL + "/product/home_recommand_more.php";

        /**
         * 登陆接口
         */
        public static String LOGIN = ROOT_URL + "/user/login_phone.php";
    }

    //根据参数发送所有post请求
    public static void postRequest(String url, RequestParams params, DisposeDataListener listener,
                                   Class<?> clazz) {
        CommonOkHttpClient.get(CommonRequest.
                createGetRequest(url, params), new DisposeDataHandle(listener, clazz));
    }

    public static void requestRecommandData(DisposeDataListener listener) {
        RequestCenter.postRequest(HttpConstants.HOME_RECOMMAND, null, listener,
                BaseRecommandModel.class);
    }

    public static void requestRecommandMore(DisposeDataListener listener) {
        RequestCenter.postRequest(HttpConstants.HOME_RECOMMAND_MORE, null, listener,
                BaseRecommandMoreModel.class);
    }

    public static void requestFriendData(DisposeDataListener listener) {
        RequestCenter.postRequest(HttpConstants.HOME_FRIEND, null, listener, BaseFriendModel.class);
    }

    /**
     * 用户登陆请求
     */
    public static void login(DisposeDataListener listener) {

        RequestParams params = new RequestParams();
        params.put("mb", "18734924592");
        params.put("pwd", "999999q");
        RequestCenter.postRequest(HttpConstants.LOGIN, params, listener, User.class);
    }
}
