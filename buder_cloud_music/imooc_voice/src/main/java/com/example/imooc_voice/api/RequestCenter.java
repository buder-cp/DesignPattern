package com.example.imooc_voice.api;


import com.example.imooc_voice.view.login.user.User;
import com.example.lib_network.okhttp.CommonOkHttpClient;
import com.example.lib_network.okhttp.request.CommonRequest;
import com.example.lib_network.okhttp.request.RequestParams;
import com.example.lib_network.okhttp.response.listener.DisposeDataHandle;
import com.example.lib_network.okhttp.response.listener.DisposeDataListener;

/**
 * 请求中心
 */
public class RequestCenter {

    static class HttpConstants {
        private static final String ROOT_URL = "http://imooc.com/api";
//        private static final String ROOT_URL = "http://39.97.122.129";

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
        CommonOkHttpClient.post(CommonRequest.
                createPostRequest(url, params), new DisposeDataHandle(listener, clazz));
    }


    /**
     * 用户登陆请求
     */
    public static void login(DisposeDataListener listener) {
        RequestParams params = new RequestParams();
        params.put("mb", "13700245171");
        params.put("pwd", "buder");
        RequestCenter.postRequest(HttpConstants.LOGIN, params, listener, User.class);
    }
}
