package com.gtechoogle.beautyrank.network;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.GetCallback;
import com.avos.avoscloud.GetDataCallback;
import com.google.gson.GsonBuilder;
import com.gtechoogle.beautyrank.Constants;
import com.gtechoogle.beautyrank.R;
import com.gtechoogle.beautyrank.bean.Beauty;
import com.squareup.picasso.Picasso;

public class DownloadManager implements DownloadInterface {
    Handler mHandler;
    public DownloadManager(Handler hanlder){
        mHandler = hanlder;
    }
    @Override
    public void sendRequest(String objectName, String id) {
        AVQuery<AVObject> avQuery = new AVQuery<>(objectName);
        avQuery.getInBackground(id, new GetCallback<AVObject>() {
            @Override
            public void done(AVObject avObject, AVException e) {
                AVFile file = (AVFile) avObject.get("content");
                file.getDataInBackground(new GetDataCallback() {
                    @Override
                    public void done(byte[] bytes, AVException e) {
                        Log.d("saved","aaa = " + new String(bytes));
                        Beauty data = new GsonBuilder().create().fromJson(new String(bytes), Beauty.class);
                        Message msg = Message.obtain();
                        msg.what = Constants.MSG_GET_DATA;
                        msg.obj = data;
                        mHandler.sendMessage(msg);
                    }
                });
            }
        });
    }

    @Override
    public void downloadPic(String url) {

    }
}
