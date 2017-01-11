package com.miguan.otk.adapter.viewholder;

import android.net.Uri;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.News;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */

public class NewsViewHolder extends BaseViewHolder<News> {

    @Bind(R.id.dv_news_thumb)
    SimpleDraweeView mDvThumb;

    @Bind(R.id.tv_news_title)
    TextView mTvTitle;

    @Bind(R.id.tv_news_date)
    TextView mTvDate;

    public NewsViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_list_news);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(News news) {
        mDvThumb.setImageURI(Uri.parse(news.getImg()[0]));
        mTvTitle.setText(news.getTitle());
        mTvDate.setText(news.getCreate_time());
    }

}
