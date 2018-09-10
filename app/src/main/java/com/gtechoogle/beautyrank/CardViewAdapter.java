package com.gtechoogle.beautyrank;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gtechoogle.beautyrank.bean.Beauty;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.ViewHolder>{
    List<Beauty.DatasheetBean> mData = new ArrayList<>();
    Context mContext;
    public CardViewAdapter(Context context) {
        this.mContext = context;
    }
    public void setRawData(Beauty data) {
        mData = data.getDatasheet();
        notifyDataSetChanged();
    }
    @Override
    public CardViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_cardview, parent, false);
        CardViewAdapter.ViewHolder viewHolder = new CardViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CardViewAdapter.ViewHolder holder, final int position) {
        String summary = mData.get(position).getInfo().getDescription();
        holder.mSummary.setText(summary);
        String url = mData.get(position).getGallery().get(0).getUrl();
        Picasso.get().load(url).into(holder.mPic);
        holder.mPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, AlbumActivity.class);
                ArrayList<String> urls = new ArrayList<>();
                for (Beauty.DatasheetBean.GalleryBean url: mData.get(position).getGallery()) {
                    urls.add(url.getUrl());
                }
                intent.putStringArrayListExtra("urls", urls);
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mSummary;
        ImageView mPic;
        public ViewHolder(View itemView) {
            super(itemView);
            mSummary = itemView.findViewById(R.id.cover_summary);
            mPic = itemView.findViewById(R.id.cover_view);
        }
    }
}
