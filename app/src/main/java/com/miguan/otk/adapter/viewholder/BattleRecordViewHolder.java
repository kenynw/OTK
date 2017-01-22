package com.miguan.otk.adapter.viewholder;

import android.content.Intent;
import android.net.Uri;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.Battle;
import com.miguan.otk.module.battle.BattleActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2016/12/20. LiaoPeiKun Inc. All rights reserved.
 */

public class BattleRecordViewHolder extends BaseViewHolder<Battle> {

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

    public BattleRecordViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_list_match_record);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(Battle battle) {
        mTvTitle.setText(battle.getTitle());
        mTvStatus.setText(battle.getResult());
        mTvUserA.setText(battle.getUser_a().getUsername());
        mDvUserA.setImageURI(Uri.parse(battle.getUser_a().getPhoto()));
        mTvUserB.setText(battle.getUser_b().getUsername());
        mDvUserB.setImageURI(Uri.parse(battle.getUser_b().getPhoto()));
        itemView.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), BattleActivity.class);
            intent.putExtra("battle_id", battle.getRecord_id());
            getContext().startActivity(intent);
        });
    }
}
