package com.miguan.otk.adapter.viewholder;

import android.content.Intent;
import android.net.Uri;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.Schedule;
import com.miguan.otk.module.match.MatchDetailActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2016/12/20. LiaoPeiKun Inc. All rights reserved.
 */

public class MatchRecordViewHolder extends BaseViewHolder<Schedule> {

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
    public void setData(Schedule against) {
        mTvTitle.setText(against.getTitle());
        mTvStatus.setText(against.getResult());
        mTvUserA.setText(against.getUser_a().getUsername());
        mDvUserA.setImageURI(Uri.parse(against.getUser_a().getPhoto()));
        mTvUserB.setText(against.getUser_b().getUsername());
        mDvUserB.setImageURI(Uri.parse(against.getUser_b().getPhoto()));
        itemView.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), MatchDetailActivity.class);
            intent.putExtra("match_id", against.getCompetition_id());
            getContext().startActivity(intent);
        });
    }
}
