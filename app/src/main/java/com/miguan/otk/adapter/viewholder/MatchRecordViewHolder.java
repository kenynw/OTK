package com.miguan.otk.adapter.viewholder;

import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.Against;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2016/12/20. LiaoPeiKun Inc. All rights reserved.
 */

public class MatchRecordViewHolder extends BaseViewHolder<Against> {

    @Bind(R.id.tv_record_title)
    TextView mTvTitle;

    @Bind(R.id.tv_match_status)
    TextView mTvStatus;

    @Bind(R.id.dv_record_user_a)
    SimpleDraweeView mDvUserA;

    @Bind(R.id.tv_record_user_a)
    TextView mTvUserA;

    @Bind(R.id.dv_record_user_b)
    SimpleDraweeView mDvUserB;

    @Bind(R.id.tv_record_user_b)
    TextView mTvUserB;

    public MatchRecordViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_list_match_record);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(Against against) {
        mTvTitle.setText(against.getTitle());
        mTvStatus.setText(against.getWinner_id() == against.getAuser_id() ? "胜-负" : "负-胜");
        mTvUserA.setText(against.getAusername());
        mTvUserB.setText(against.getBusername());
    }
}
