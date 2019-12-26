package ctrip.business;

import android.app.Application;
import android.content.Context;

import ctrip.business.event.Distributor;


public class BaseApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Distributor.init();
    }
}
