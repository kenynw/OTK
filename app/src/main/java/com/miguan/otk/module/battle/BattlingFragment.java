package com.miguan.otk.module.battle;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dsk.chain.bijection.ChainFragment;
import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.list.DividerGridItemDecoration;
import com.miguan.otk.R;
import com.miguan.otk.adapter.PickAdapter;
import com.miguan.otk.model.bean.Battle;
import com.miguan.otk.module.match.SubmitShotActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2017/1/21. LiaoPeiKun Inc. All rights reserved.
 */

@RequiresPresenter(BattlingPresenter.class)
public class BattlingFragment extends ChainFragment<BattlingPresenter> {

    @Bind(R.id.btn_battling_judge)
    Button mBtnContact;

    @Bind(R.id.btn_battling_screenshot)
    Button mBtnScreenshot;

    @Bind(R.id.btn_battling_chatroom)
    Button mBtnChatroom;

    @Bind(R.id.tv_battle_status_title)
    TextView mTvTitle;

    @Bind(R.id.tv_battle_status_notice)
    TextView mTvNotice;

    @Bind(R.id.tv_battle_status_desc)
    TextView mTvDesc;

    @Bind(R.id.rcv_battling_list)
    RecyclerView mRecycle;

    @Bind(R.id.btn_battling_save)
    Button mBtnPick;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.battle_fragment_battling, container, false);
        ButterKnife.bind(this, view);

        mBtnContact.setOnClickListener(v -> startActivity(new Intent(getActivity(), ContactJudgeActivity.class)));
        mBtnScreenshot.setOnClickListener(v -> startActivity(new Intent(getActivity(), SubmitShotActivity.class)));
//        mBtnChatroom.setOnClickListener(v -> startActivity(new Intent()));

        mRecycle.setLayoutManager(new GridLayoutManager(getActivity(), 5));
        mRecycle.addItemDecoration(new DividerGridItemDecoration(getActivity()));

        return view;
    }

    public void setData(Battle battle) {
        if (battle.getBattle_status() == 2) { // pick
            mTvTitle.setText(R.string.text_pick_title);
            mTvNotice.setText(R.string.text_pick_notice);
            mTvDesc.setText(R.string.text_pick_desc);

            PickAdapter adapter = new PickAdapter(getActivity());
            mRecycle.setAdapter(adapter);
            mBtnPick.setOnClickListener(v -> getPresenter().pick(adapter.getSelected()));
        } else if (battle.getBattle_status() == 3) { // ban
            mTvTitle.setText(R.string.text_ban_title);
            mTvNotice.setText(R.string.text_ban_notice);
            mTvDesc.setText(R.string.text_ban_desc);

            PickAdapter adapter = new PickAdapter(getActivity(), new String[] {"德鲁伊", "法师", "猎人", "牧师"});
            mRecycle.setAdapter(adapter);
            mBtnPick.setOnClickListener(v -> getPresenter().ban(""));
        }
    }

    public void setPicked() {
        mTvNotice.setVisibility(View.GONE);
        mTvDesc.setText(R.string.text_picked_desc);
    }

    public void setBaned() {
        mTvNotice.setVisibility(View.GONE);
        mTvDesc.setText(R.string.text_baned_desc);
    }
}
