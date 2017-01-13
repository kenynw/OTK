package com.miguan.otk.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.Banner;
import com.miguan.otk.module.common.WebActivity;
import com.miguan.otk.module.match.MatchDetailActivity;
import com.miguan.otk.module.news.NewsDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */

public class BannerPagerAdapter extends PagerAdapter {

    private Context mContext;

    private List<Banner> mBannerList;

    private List<View> mViewList;

    public BannerPagerAdapter(Context context, List<Banner> banners) {
        this.mContext = context;
        this.mBannerList = banners;

        mViewList = new ArrayList<>();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(mContext.getResources());
        GenericDraweeHierarchy hierarchy = builder
                .setFadeDuration(300)
                .setPlaceholderImage(R.mipmap.def_image_loading)
                .setPlaceholderImageScaleType(ScalingUtils.ScaleType.CENTER_CROP)
                .setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP)
                .build();
        SimpleDraweeView dv = new SimpleDraweeView(mContext, hierarchy);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dv.setLayoutParams(lp);

        Banner banner = mBannerList.get(position);
        dv.setImageURI(Uri.parse(banner.getImg()));
        dv.setOnClickListener(v -> {
            switch (banner.getType()) {
                case 1 :
                    Intent newsIntent = new Intent(mContext, NewsDetailActivity.class);
                    newsIntent.putExtra("url", banner.getUrl());
                    mContext.startActivity(newsIntent);
                    break;
                case 2 :
                    Intent matchIntent = new Intent(mContext, MatchDetailActivity.class);
                    matchIntent.putExtra("match_id", banner.getUrl());
                    mContext.startActivity(matchIntent);
                    break;
                default :
                    Intent intent = new Intent(mContext, WebActivity.class);
                    intent.putExtra("url", banner.getUrl());
                    mContext.startActivity(intent);
                    break;
            }
        });

        container.addView(dv);
        mViewList.add(dv);
        return dv;
    }

    @Override
    public int getCount() {
        return mBannerList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViewList.get(position));
    }
}
