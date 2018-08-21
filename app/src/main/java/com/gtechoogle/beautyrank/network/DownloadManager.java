package com.gtechoogle.beautyrank.network;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.GetCallback;
import com.avos.avoscloud.GetDataCallback;
import com.google.gson.GsonBuilder;
import com.gtechoogle.beautyrank.R;
import com.gtechoogle.beautyrank.bean.Beauty;
import com.squareup.picasso.Picasso;

public class DownloadManager implements DownloadInterface {
    @Override
    public void downloadJson(String objectName, String id) {
        AVQuery<AVObject> avQuery = new AVQuery<>(objectName);
        avQuery.getInBackground(id, new GetCallback<AVObject>() {
            @Override
            public void done(AVObject avObject, AVException e) {
                AVFile file = (AVFile) avObject.get("content");
                file.getDataInBackground(new GetDataCallback() {
                    @Override
                    public void done(byte[] bytes, AVException e) {
                        Log.d("saved","aaa = " + new String(bytes));
                        Beauty testGson = new GsonBuilder().create().fromJson(new String(bytes), Beauty.class);
//                        String url = testGson.getDatasheet().get(0).getGallery().get(0).getUrl();
//                        return testGson;

                    }
                });
            }
        });
    }

    @Override
    public void downloadPic(String url) {

    }
}
