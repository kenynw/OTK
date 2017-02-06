package com.miguan.otk.module.battle;

import android.os.Bundle;

import com.dsk.chain.expansion.data.BaseDataActivityPresenter;
import com.miguan.otk.model.bean.Battle;

/**
 * Copyright (c) 2017/1/3. LiaoPeiKun Inc. All rights reserved.
 */

public class ShotDetailPresenter extends BaseDataActivityPresenter<ShotDetailActivity, Battle> {

    private Battle mBattle;

    @Override
    protected void onCreate(ShotDetailActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mBattle = getView().getIntent().getParcelableExtra("battle");
    }

    @Override
    protected void onCreateView(ShotDetailActivity view) {
        super.onCreateView(view);
        publishObject(mBattle);
    }
}
