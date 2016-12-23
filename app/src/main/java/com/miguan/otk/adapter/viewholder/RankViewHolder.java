package com.miguan.otk.adapter.viewholder;

import android.net.Uri;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.User;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2016/11/24. LiaoPeiKun Inc. All rights reserved.
 */

public class RankViewHolder extends BaseViewHolder<User> {

    @Bind(R.id.dv_rank_avatar)
    SimpleDraweeView mDvAvatar;

    @Bind(R.id.tv_rank_username)
    TextView mTvUsername;

    @Bind(R.id.tv_rank_points)
    TextView mTvPoints;

    public RankViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_list_rank);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(User data) {
        mDvAvatar.setImageURI(Uri.parse(data.getAvatar()));
        mTvUsername.setText(data.getName());
        mTvPoints.setText(data.getPoints());
    }
}
