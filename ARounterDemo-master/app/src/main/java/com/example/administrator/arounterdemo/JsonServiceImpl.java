package com.example.administrator.arounterdemo;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.SerializationService;
import com.google.gson.Gson;

import java.lang.reflect.Type;

@Route(path = "/service/json")
public class JsonServiceImpl implements SerializationService {

    private Gson mGson;

    @Override
    public void init(Context context) {
        mGson = new Gson();

    }

    @Override
    public <T> T json2Object(String text, Class<T> clazz) {
        checkJson();
        return mGson.fromJson(text, clazz);
        //        return JSON.parseObject(text, clazz);
    }

    @Override
    public String object2Json(Object instance) {
        checkJson();
        return mGson.toJson(instance);

        //        return JSON.toJSONString(instance);
    }

    @Override
    public <T> T parseObject(String input, Type clazz) {
        checkJson();
        return mGson.fromJson(input, clazz);
        //        return JSON.parseObject(input, clazz);
    }

    public void checkJson() {
        if (mGson == null) {
            mGson = new Gson();
        }
    }
}
