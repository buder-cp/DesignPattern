package com.example.facadetoagentmode.proxy;

public class HttpProxy implements IHttp {

    private static IHttp mHttp = null;

    private static HttpProxy mInstance;

    private HttpProxy() {
        mInstance = this;
    }

    public static HttpProxy obtain() {
        if (mInstance == null) {
            synchronized (OkHttpModel.class) {
                if (mInstance == null) {
                    mInstance = new HttpProxy();
                }
            }
        }
        return mInstance;
    }

    public static void init(IHttp http) {
        mHttp = http;
    }

    @Override
    public void get(String url, ICallBack callBack) {
        mHttp.get(url, callBack);
    }
}
