package com.example.administrator.arounterdemo.testService;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * @author xujun  on 2/7/2018.
 */
public interface HelloService extends IProvider {
    String sayHello(String name);
}
