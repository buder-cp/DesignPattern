package ctrip.business;

import android.app.Application;
import android.content.Context;

import ctrip.business.event.EventBus;


public class BaseApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        EventBus.init();
    }
}
