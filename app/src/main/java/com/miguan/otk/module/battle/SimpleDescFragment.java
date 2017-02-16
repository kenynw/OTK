package com.miguan.otk.module.battle;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dsk.chain.bijection.ChainFragment;
import com.dsk.chain.bijection.RequiresPresenter;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.Battle;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2017/1/6. LiaoPeiKun Inc. All rights reserved.
 */

@RequiresPresenter(SImpleDescPresenter.class)
public class SimpleDescFragment extends ChainFragment<SImpleDescPresenter> {

    @Bind(R.id.tv_battle_status_title)
    TextView mTvTitle;

    @Bind(R.id.tv_battle_status_desc)
    TextView mTvDesc;

    @Bind(R.id.btn_battle_status_save)
    Button mBtnSave;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.battle_fragment_ready, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    public void setData(Battle battle) {
        if (battle.getBattle_status() == 5) {
            mTvTitle.setText(R.string.text_battle_ended);
            mTvDesc.setText(String.format(getString(R.string.text_battle_ended_desc), battle.getWinner_id() == battle.getA_user_id() ? battle.getA_username() : (battle.getWinner_id() == battle.getB_user_id() ? battle.getB_username() : "无结果")));

            if (battle.getWinner_id() == 1 && battle.getNext_battle_id() > 0) {
                mBtnSave.setText("进入下一轮");
                mBtnSave.setOnClickListener(v -> EventBus.getDefault().post(battle));
            } else if (battle.getIs_end()) {
                mBtnSave.setVisibility(View.GONE);
            } else {
                mBtnSave.setText("结束");
                mBtnSave.setOnClickListener(v -> {
                    Intent intent = new Intent(getActivity(), BattleActivity.class);
                    intent.putExtra("battle_id", battle.getBattle_id());
                    startActivity(intent);
                });
            }
        } else if (battle.getUser_type() == 0 || battle.getUser_type() == 3) {
            mBtnSave.setVisibility(View.GONE);
            mTvTitle.setText("比赛阶段");
            mTvDesc.setText(String.format("%s阶段比赛正在进行中", battle.getBattle_times()));
        } else if (battle.getBattle_status() == 0) {
            mTvTitle.setText("等待对手生成");
            mTvDesc.setText("您的对手还没有结束当前比赛，请耐心等待");
            mBtnSave.setEnabled(false);
        } else if (battle.getBattle_status() == 1) {
            if ((battle.getUser_type() == 1 && battle.getBattle_status_user() == 2) || (battle.getUser_type() == 2 && battle.getBattle_status_user() == 3)) {
                mBtnSave.setText("已准备");
                mBtnSave.setEnabled(false);
            } else {
                mBtnSave.setText("准备");
                mBtnSave.setOnClickListener(v -> getPresenter().ready(battle.getBattle_id()));
            }
        }

    }
}
