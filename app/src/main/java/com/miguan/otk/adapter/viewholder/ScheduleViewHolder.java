package com.miguan.otk.adapter.viewholder;

import android.content.Intent;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.Schedule;
import com.miguan.otk.module.battle.BattleActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2016/12/23. LiaoPeiKun Inc. All rights reserved.
 */

public class ScheduleViewHolder extends BaseViewHolder<Schedule> {

    @Bind(R.id.tv_schedule_a_score)
    TextView mTvAScore;

    @Bind(R.id.tv_schedule_a_username)
    TextView mTvAUsername;

    @Bind(R.id.tv_schedule_b_score)
    TextView mTvBScore;

    @Bind(R.id.tv_schedule_b_username)
    TextView mTvBUsername;

    public ScheduleViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_list_schedule);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(Schedule schedule) {
        mTvAScore.setBackgroundColor(getContext().getResources().getColor(
                schedule.getWinner_id() == 1 ? R.color.colorPrimaryDark : R.color.white
        ));
        mTvAScore.setTextColor(getContext().getResources().getColor(
                schedule.getWinner_id() == 1 ? R.color.white : R.color.textBody
        ));
        mTvBScore.setBackgroundColor(getContext().getResources().getColor(
                schedule.getWinner_id() == 2 ? R.color.colorPrimaryDark : R.color.white)
        );
        mTvBScore.setTextColor(getContext().getResources().getColor(
                schedule.getWinner_id() == 2 ? R.color.white : R.color.textBody
        ));
        mTvAScore.setText(schedule.getA_score() + "");
        mTvAUsername.setText(schedule.getA_name());
        mTvBScore.setText(schedule.getB_score() + "");
        mTvBUsername.setText(schedule.getB_name());

        itemView.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), BattleActivity.class);
            intent.putExtra("battle_id", schedule.getBattle_id());
            getContext().startActivity(intent);
        });
    }
}
