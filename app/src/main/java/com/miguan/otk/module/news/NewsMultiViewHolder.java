package com.miguan.otk.module.news;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.exgridview.ExGridView;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.News;
import com.sgun.utils.LUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2017/1/11. LiaoPeiKun Inc. All rights reserved.
 */

public class NewsMultiViewHolder extends BaseViewHolder<News> {

    @Bind(R.id.tv_news_title)
    TextView mTvTitle;

    @Bind(R.id.grid_news_images)
    ExGridView mGridImages;

    @Bind(R.id.tv_news_date)
    TextView mTvDate;

    public NewsMultiViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_list_news_multi);
        ButterKnife.bind(this, itemView);
        if (LUtils.getScreenWidth() >= 1080) {
            mTvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, getContext().getResources().getDimension(R.dimen.text_size_title_material));
        }
    }

    @Override
    public void setData(News news) {
        mTvTitle.setText(news.getTitle());
        mTvDate.setText(news.getCreate_time());

        int height = (2 * (LUtils.getScreenWidth() - 4 * LUtils.dp2px(8)) / 9);
        ViewGroup.LayoutParams layoutParams = mGridImages.getLayoutParams();
        layoutParams.height = height;
        mGridImages.setLayoutParams(layoutParams);

        int width = (LUtils.getScreenWidth() - 4 * LUtils.dp2px(8)) / 3;
        for (int i=0; i<news.getImg().length; i++) {
            SimpleDraweeView dv = (SimpleDraweeView) LayoutInflater.from(getContext()).inflate(R.layout.item_grid_image, mGridImages, false);
            dv.setImageURI(news.getImg()[i]);

            mGridImages.addView(dv, width, height);
        }
    }

}
