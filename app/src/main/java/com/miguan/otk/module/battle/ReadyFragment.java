package com.miguan.otk.module.battle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dsk.chain.bijection.ChainFragment;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.Battle;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2017/1/6. LiaoPeiKun Inc. All rights reserved.
 */

public class ReadyFragment extends ChainFragment<ReadyPresenter> {

    @Bind(R.id.btn_battle_status)
    Button mBtnStatus;

    private Battle mBattle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().containsKey("battle")) {
            mBattle = getArguments().getParcelable("battle");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.battle_fragment_ready, container, false);
        ButterKnife.bind(this, view);

        if (mBattle.getBattle_status() == 1) {
            mBtnStatus.setText("准备");
            mBtnStatus.setOnClickListener(v -> getPresenter().ready(mBattle.getBattle_id()));
        } else {
            mBtnStatus.setText("已准备");
            mBtnStatus.setEnabled(false);
        }

        return view;
    }
}
