package com.miguan.otk.module.match;

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
 * Copyright (c) 2016/12/23. LiaoPeiKun Inc. All rights reserved.
 */

public class UserViewHolder extends BaseViewHolder<User> {

    @Bind(R.id.dv_enroll_avatar)
    SimpleDraweeView mDvAvatar;

    @Bind(R.id.tv_enroll_username)
    TextView mTvUsername;

    public UserViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_list_enroll);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(User data) {
        mDvAvatar.setImageURI(Uri.parse(data.getAvatar()));
        mTvUsername.setText(data.getName());
    }
}
