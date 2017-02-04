package com.miguan.otk.module.battle;

import android.os.Bundle;

import com.dsk.chain.expansion.data.BaseDataActivityPresenter;
import com.miguan.otk.model.BattleModel;
import com.miguan.otk.model.bean.Battle;
import com.miguan.otk.model.services.ServicesResponse;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Copyright (c) 2017/1/3. LiaoPeiKun Inc. All rights reserved.
 */

public class BattlePresenter extends BaseDataActivityPresenter<BattleActivity, Battle> {

    private int mBattleID;

    @Override
    protected void onCreate(BattleActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        EventBus.getDefault().register(this);
        mBattleID = getView().getIntent().getIntExtra("battle_id", 0);
    }

    @Override
    protected void onCreateView(BattleActivity view) {
        super.onCreateView(view);
        setData(null);
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void setData(Battle battle) {
        BattleModel.getInstance().getBattleDetail(mBattleID).unsafeSubscribe(new ServicesResponse<Battle>() {
            @Override
            public void onNext(Battle battle) {
                getView().setData(battle);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
