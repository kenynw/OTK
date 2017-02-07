package com.miguan.otk.adapter.viewholder;

import android.content.Intent;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.Battle;
import com.miguan.otk.module.battle.BattleActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2016/12/23. LiaoPeiKun Inc. All rights reserved.
 */

public class ScheduleViewHolder extends BaseViewHolder<Battle> {

    @Bind(R.id.tv_vs_player_a)
    TextView mTvA;

    public ScheduleViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_list_against);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(Battle battle) {
        mTvA.setText(String.format("%s VS %s", battle.getA_username(), battle.getB_username()));
        itemView.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), BattleActivity.class);
            intent.putExtra("battle_id", battle.getBattle_id());
            getContext().startActivity(intent);
        });
    }
}
