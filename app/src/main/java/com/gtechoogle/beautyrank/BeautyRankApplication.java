package com.gtechoogle.beautyrank;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

public class BeautyRankApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        AVOSCloud.initialize(this,"9lfX0v0OP2W1GquYUHjFVxnJ-gzGzoHsz","ShoVq7eFtV7eV1PDT6deCTNg");
        AVOSCloud.setDebugLogEnabled(true);

    }
}
