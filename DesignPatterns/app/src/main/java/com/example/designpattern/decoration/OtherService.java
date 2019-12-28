package com.example.designpattern.decoration;


/**
 * 抽象装饰者：除了小姐姐聊天基本服务外的，其他服务
 * 例如：唇唇欲动、霸王别姬、逼上梁山等等
 */
public class OtherService extends GirlService {

    private GirlService girlService;

    public OtherService(GirlService service) {
        this.girlService = service;
    }

    @Override
    public void liaoTianService() {
        girlService.liaoTianService();
    }
}
