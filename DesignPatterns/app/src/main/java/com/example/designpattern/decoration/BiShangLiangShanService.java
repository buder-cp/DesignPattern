package com.example.designpattern.decoration;

public class BiShangLiangShanService extends OtherService {


    public BiShangLiangShanService(GirlService service) {
        super(service);
    }

    public void biShangLiangShanService() {
        super.liaoTianService();
        System.out.println("装饰器的具体实现：逼上梁山服务");
    }
}

