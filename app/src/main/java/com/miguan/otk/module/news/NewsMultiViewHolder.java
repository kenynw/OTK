package com.miguan.otk.module.news;

import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.News;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2017/1/11. LiaoPeiKun Inc. All rights reserved.
 */

public class NewsMultiViewHolder extends BaseViewHolder<News> {

    @Bind(R.id.tv_news_title)
    TextView mTvTitle;

    @Bind(R.id.rcv_news_images)
    RecyclerView mRcvImages;

    @Bind(R.id.tv_news_date)
    TextView mTvDate;

    public NewsMultiViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_list_news_multi);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(News news) {
        mTvTitle.setText(news.getTitle());
        mTvDate.setText(news.getCreate_time());
        mRcvImages.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mRcvImages.setAdapter(new ImageAdapter(news.getImg()));
    }

    private class ImageAdapter extends RecyclerView.Adapter<ImageVH> {

        private String[] mImages;

        ImageAdapter(String[] images) {
            mImages = images;
        }

        @Override
        public ImageVH onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_grid_image, parent, false);
            return new ImageVH(view);
        }

        @Override
        public void onBindViewHolder(ImageVH holder, int position) {
            holder.mDvImage.setImageURI(Uri.parse(mImages[position]));
        }

        @Override
        public int getItemCount() {
            return mImages.length;
        }
    }

    class ImageVH extends RecyclerView.ViewHolder {

        @Bind(R.id.dv_item_image)
        SimpleDraweeView mDvImage;

        public ImageVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

}
