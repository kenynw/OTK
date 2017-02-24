package com.miguan.otk.module.news;

import android.net.Uri;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.News;
import com.miguan.otk.utils.LUtils;

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
        if (LUtils.getScreenWidth() >= 1080) {
            mTvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, getContext().getResources().getDimension(R.dimen.text_size_title_material));
        }
    }

    @Override
    public void setData(News news) {
        int width = (LUtils.getScreenWidth() - LUtils.dp2px(8) * 4) / 3;
        int height = (2 * (LUtils.getScreenWidth() - 4 * LUtils.dp2px(8)) / 9);
        ViewGroup.LayoutParams lp = mDvThumb.getLayoutParams();
        lp.width = width;
        lp.height = height;
        mDvThumb.setLayoutParams(lp);
        mDvThumb.setImageURI(Uri.parse(news.getImg()[0]));

        mTvTitle.setText(news.getTitle());
        mTvDate.setText(news.getCreate_time());
    }

}
