package com.miguan.otk.adapter.viewholder;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miguan.otk.R;
import com.miguan.otk.model.MatchModel;
import com.miguan.otk.model.bean.Mission;
import com.miguan.otk.model.services.ServicesResponse;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2016/12/23. LiaoPeiKun Inc. All rights reserved.
 */

public class MissionViewHolder extends BaseViewHolder<Mission> {

    private static final int[] ICONS = new int[] {R.mipmap.ic_mission_enroll, R.mipmap.ic_mission_against, R.mipmap.ic_mission_winner};

    @Bind(R.id.iv_mission_image)
    ImageView mIvIcon;

    @Bind(R.id.tv_mission_name)
    TextView mTvName;

    @Bind(R.id.tv_mission_desc)
    TextView mTvDesc;

    @Bind(R.id.btn_mission_dole)
    Button mBtnDole;

    public MissionViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_list_mission);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(Mission data) {
        mIvIcon.setImageResource(ICONS[getLayoutPosition() % ICONS.length]);
        mTvName.setText(data.getTitle());
        mTvDesc.setText(data.getComment() + " +" + data.getScore() + "撒币");
        if (data.getStatus() == 0) {
            mBtnDole.setText("领取奖励");
            mBtnDole.setEnabled(true);
            mBtnDole.setOnClickListener(v -> {
                MatchModel.getInstance().doleMission(data.getMission_id()).unsafeSubscribe(new ServicesResponse<Mission>() {
                    @Override
                    public void onNext(Mission mission) {
                        mBtnDole.setEnabled(false);
                        mBtnDole.setText("已完成");
                    }
                });
            });
        } else if (data.getStatus() == 1) {
            mBtnDole.setText("已完成");
            mBtnDole.setEnabled(false);
        } else {
            mBtnDole.setText("去完成");
            mBtnDole.setEnabled(true);
            mBtnDole.setOnClickListener(v -> ((Activity) getContext()).finish());
        }
    }

}
