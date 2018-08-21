package com.gtechoogle.beautyrank;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.GetCallback;
import com.avos.avoscloud.GetDataCallback;
import com.avos.avoscloud.ProgressCallback;
import com.avos.avoscloud.SaveCallback;
import com.avos.avoscloud.SignUpCallback;
import com.google.gson.GsonBuilder;
import com.gtechoogle.beautyrank.bean.Gallery;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    AVQuery<AVObject> avQuery = new AVQuery<>("DailyData");
                    avQuery.getInBackground("5b7ae77c67f356005f9b0589", new GetCallback<AVObject>() {
                        @Override
                        public void done(AVObject avObject, AVException e) {
                            String url = (String) avObject.get("url");
                            Log.d("saved","url = " + url);
                            AVFile file = (AVFile) avObject.get("content");
                            file.getDataInBackground(new GetDataCallback() {
                                @Override
                                public void done(byte[] bytes, AVException e) {
                                    Log.d("saved","aaa = " + new String(bytes));
                                    Gallery testGson = new GsonBuilder().create().fromJson(new String(bytes), Gallery.class);
                                    List<Gallery.GalleryBean> p0 = testGson.getGallery();
                                    String kl = p0.get(1).getName();
                                    Log.d("saved","kl = " + kl);
                                    String link = p0.get(1).getUrl().get(0).getLink();
                                    Picasso.get().load(link).into(imageView);
                                }
                            });
                        }
                    });
//                    AVFile file = AVFile.withObjectId("5b7ae77c67f356005f9b0589");
//                    file.getDataInBackground(new GetDataCallback() {
//                        @Override
//                        public void done(byte[] bytes, AVException e) {
//                            // bytes 就是文件的数据流
//                            Log.d("saved","bytes = " + Arrays.toString(bytes));
//                            try {
//                                JSONObject jsonObject = null;
//                                jsonObject = new JSONObject(new String(bytes));
//                                JSONArray jsonObject1 = jsonObject.getJSONArray("gallery");
//                                for (int i=0;i<jsonObject1.length();i++) {
//                                    JSONObject item = (JSONObject) jsonObject1.get(i);
//                                    String name =  item.getString("name");
//                                    Log.d("saved","name = " + name);
//                                    JSONArray urls = item.getJSONArray("url");
//                                    for (int j =0;j<urls.length();j++){
//                                        JSONObject op = (JSONObject) urls.get(j);
//                                        String url = op.getString("link");
//                                        Log.d("saved","url = " + url);
//                                        Picasso.get().load(url).into(imageView);
//                                    }
//                                }
//                                Gallery testGson = new GsonBuilder().create().fromJson(new String(bytes), Gallery.class);
//                                List<Gallery.GalleryBean> p0 = testGson.getGallery();
//                                String kl = p0.get(0).getName();
//                                Log.d("saved","kl = " + kl);
////                                String name = (JSONObject) (jsonObject1.get(0)).getString("name");
////                                JSONArray urls = jsonObject1.getJSONArray("url");
//////                                Log.d("saved", testV);
////                                String abc = new JSONObject(new String(bytes)).toString();
////                                Log.d("saved","=============");
////                                Log.d("saved",name);
////                                Log.d("saved", abc);
////                                Log.d("saved","value = " + urls);
////                                JSONObject link = (JSONObject) urls.get(0);
////                                String datalink = link.getString("link");
////                                Log.d("saved","url= "+ datalink);
////                                ImageView imageView = findViewById(R.id.image);
//////                                datalink = "http://i.imgur.com/DvpvklR.png";
////                                Picasso.get().load(datalink).into(imageView);
//
//                            } catch (JSONException e1) {
//                                e1.printStackTrace();
//                                Log.d("saved","error="+ e1);
//
//                            }
//                        }
//                    }, new ProgressCallback() {
//                        @Override
//                        public void done(Integer integer) {
//                            // 下载进度数据，integer 介于 0 和 100。
//                            Log.d("saved","progress = " + integer);
//                        }
//                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("saved","Go to dead");
                }
            }
        }).start();
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
}
