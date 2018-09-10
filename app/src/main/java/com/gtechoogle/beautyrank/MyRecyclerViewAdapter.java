package com.gtechoogle.beautyrank;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    List<String> item;
    Context mCon;

    public MyRecyclerViewAdapter(List<String> list, Context context) {
        this.item = list;
        mCon = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_normal, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.ivImage.setBackground(mCon.getDrawable(R.drawable.time));
        final String url = item.get(position);
        Picasso.get().load(url).into(holder.ivImage);
        holder.ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mCon,FullPicActivity.class);
                intent.putExtra("url", url);
                mCon.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        ViewHolder(View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.item_tx);
//            int width = ((Activity) ivImage.getContext()).getWindowManager().getDefaultDisplay().getWidth();
//            ViewGroup.LayoutParams params = ivImage.getLayoutParams();
//            //设置图片的相对于屏幕的宽高比
//            params.width = width/3;
//            params.height =  (int) (200 + Math.random() * 400) ;
//            ivImage.setLayoutParams(params);
        }
    }
}
