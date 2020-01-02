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

    /**
     * 代理的扩展属性和方法增强性
     * @param url
     * @param callBack
     */
    @Override
    public void get(String url, ICallBack callBack) {
        /**
         * 代理类的作用：
         * 1.可以在调用真正服务的类之前，做一些自己额外的任务，例如收取手续费，请求重定向等等;
         * 代理类可以实现拦截方法，修改原方法的参数和返回值，满足了代理自身需求和目的，也就
         * 是代理的方法增强性。
         * 2.内部对象因为某个原因换了个名或者换了个方法字段等等，那对访问者来说一点不影响，
         * 因为他拿到的只是代理类而已，从而使该访问对象具有高扩展性
         */
        mHttp.get(url, callBack);
    }
}
