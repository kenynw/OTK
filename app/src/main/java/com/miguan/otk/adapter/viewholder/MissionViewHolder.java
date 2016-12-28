package com.miguan.otk.adapter.viewholder;

import android.net.Uri;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.Mission;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2016/12/23. LiaoPeiKun Inc. All rights reserved.
 */

public class MissionViewHolder extends BaseViewHolder<Mission> {

    @Bind(R.id.dv_mission_image)
    SimpleDraweeView mDvImage;

    @Bind(R.id.tv_mission_name)
    TextView mTvName;

    @Bind(R.id.tv_mission_desc)
    TextView mTvDesc;

    @Bind(R.id.tv_mission_bonus)
    TextView mTvBonus;

    public MissionViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_list_mission);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(Mission data) {
        mDvImage.setImageURI(Uri.parse(data.getMission_image()));
        mTvName.setText(data.getMission_name());
        mTvDesc.setText(data.getMission_desc());
        mTvBonus.setText(data.getMission_bonus());
    }

}
