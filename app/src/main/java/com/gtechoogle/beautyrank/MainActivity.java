package com.gtechoogle.beautyrank;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;
import com.avos.avoscloud.SignUpCallback;
import com.gtechoogle.beautyrank.bean.Beauty;
import com.gtechoogle.beautyrank.network.DownloadManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    ImageView imageView;
    DownloadManager downloadManager;
    Beauty mBeautyData;
    List<String> urls = new ArrayList<>();
    int mIndex = 0;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Constants.MSG_GET_DATA:
                    handleData(msg);
            }
        }
    };

    private void handleData(Message msg) {
        if (msg.obj instanceof Beauty )
        mBeautyData = (Beauty) msg.obj;
        for (Beauty.DatasheetBean datasheet : mBeautyData.getDatasheet()) {
            for (Beauty.DatasheetBean.GalleryBean url: datasheet.getGallery()) {
                urls.add(url.getUrl());
            }
        }
        Log.d("saved", "value= " + urls);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        downloadManager = new DownloadManager(mHandler);
        imageView = findViewById(R.id.image);
        AVObject testObject = new AVObject("TestObject");
        testObject.put("words","Hello World!");
        Log.d("saved","start!");
        testObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if(e == null){
                    Log.d("saved","success!");
                }
            }
        });
//        String currentUsername = AVUser.getCurrentUser().getUsername();
//        String currentEmail = AVUser.getCurrentUser().getEmail();
//        Log.d("saved","currentUsername =" + currentUsername + " currentEmail = " + currentEmail);
    }

    public void add_user(View view) {
        Log.d("saved","onclick");
        AVUser user = new AVUser();// 新建 AVUser 对象实例
        user.setUsername("Tom");// 设置用户名
        user.setPassword("cat!@#123");// 设置密码
        user.setEmail("tom@leancloud.cn");// 设置邮箱
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    // 注册成功
                    Log.d("saved","register done");

                } else {
                    // 失败的原因可能有多种，常见的是用户名已经存在。
                    Log.d("saved","register fail = " + e);

                }
            }
        });
    }
    public void download_json(View view) {
        downloadManager.sendRequest("DailyData",getString(R.string.datasheet_id));

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    AVQuery<AVObject> avQuery = new AVQuery<>("DailyData");
//                    avQuery.getInBackground(getString(R.string.datasheet_id), new GetCallback<AVObject>() {
//                        @Override
//                        public void done(AVObject avObject, AVException e) {
//                            String url = (String) avObject.get("url");
//                            Log.d("saved","url = " + url);
//                            AVFile file = (AVFile) avObject.get("content");
//                            file.getDataInBackground(new GetDataCallback() {
//                                @Override
//                                public void done(byte[] bytes, AVException e) {
//                                    Log.d("saved","aaa = " + new String(bytes));
//                                    Beauty testGson = new GsonBuilder().create().fromJson(new String(bytes), Beauty.class);
//                                    String url = testGson.getDatasheet().get(0).getGallery().get(0).getUrl();
//                                    Picasso.get().load(url).into(imageView);
//                                }
//                            });
//                        }
//                    });
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    Log.d("saved","Go to dead");
//                }
//            }
//        }).start();
//        try {
//            AVFile file = AVFile.withObjectId("5b6cf9c267f3560035a9fced");
//            file.getDataInBackground(new GetDataCallback() {
//                @Override
//                public void done(byte[] bytes, AVException e) {
//                    // bytes 就是文件的数据流
//                    Log.d("saved","bytes = " + bytes);
//                }
//            }, new ProgressCallback() {
//                @Override
//                public void done(Integer integer) {
//                    // 下载进度数据，integer 介于 0 和 100。
//                    Log.d("saved","progress = " + integer);
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public void Json(View view) {
    }

    public void next_pic(View view) {
        if (mIndex >= urls.size()) {
            mIndex = 0;
        }
        String url = urls.get(mIndex);
        Picasso.get().load(url).into(imageView);
        mIndex ++;
    }

    public void like_this(View view) {
    }
}
