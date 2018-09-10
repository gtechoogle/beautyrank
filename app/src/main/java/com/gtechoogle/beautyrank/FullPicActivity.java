package com.gtechoogle.beautyrank;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class FullPicActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_pic);
        ImageView imageView = findViewById(R.id.full_size);
        String url =  getIntent().getStringExtra("url");
        Picasso.get().load(url).into(imageView);

    }
}
