package com.example.designpattern.decoration;

public class LipsAndLipsService extends OtherService {

    public LipsAndLipsService(GirlService service) {
        super(service);
    }

    public void lipsAndLipsService() {
        super.liaoTianService();
        System.out.println("装饰器的具体实现：唇唇欲动服务");
    }

}
