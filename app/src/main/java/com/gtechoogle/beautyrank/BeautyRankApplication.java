package com.gtechoogle.beautyrank;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

public class BeautyRankApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        AVOSCloud.initialize(this,getString(R.string.appid),getString(R.string.clientkey));
        AVOSCloud.setDebugLogEnabled(true);
    }
}
