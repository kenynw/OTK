package com.miguan.otk.adapter.viewholder;

import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miguan.otk.model.bean.Area;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2017/1/19. LiaoPeiKun Inc. All rights reserved.
 */

public class AreaViewHolder extends BaseViewHolder<Area> {
    @Bind(android.R.id.text1)
    TextView mTvText;

    public AreaViewHolder(ViewGroup parent) {
        super(parent, android.R.layout.simple_expandable_list_item_1);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(Area data) {
        mTvText.setText(data.getName());
    }
}
