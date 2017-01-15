package com.miguan.otk.adapter.viewholder;

import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.Schedule;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2016/12/23. LiaoPeiKun Inc. All rights reserved.
 */

public class ScheduleViewHolder extends BaseViewHolder<Schedule> {

    @Bind(R.id.tv_vs_player_a)
    TextView mTvA;

    public ScheduleViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_list_against);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(Schedule against) {
        mTvA.setText(String.format("%s VS %s", against.getUser_a().getUsername(), against.getUser_b().getUsername()));
    }
}
