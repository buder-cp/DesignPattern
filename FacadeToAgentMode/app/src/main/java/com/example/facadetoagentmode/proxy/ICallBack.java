package com.example.facadetoagentmode.proxy;

public interface ICallBack<T> {
    void onSuccess(T respone);
    void onFailed(String failed);
}
