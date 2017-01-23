package com.miguan.otk.module.battle;

import android.os.Bundle;

import com.dsk.chain.bijection.Presenter;
import com.miguan.otk.model.BattleModel;
import com.miguan.otk.model.bean.Battle;
import com.miguan.otk.model.services.ServicesResponse;

import org.greenrobot.eventbus.EventBus;

/**
 * Copyright (c) 2017/1/21. LiaoPeiKun Inc. All rights reserved.
 */

public class ReadyPresenter extends Presenter<ReadyFragment> {

    private Battle mBattle;

    @Override
    protected void onCreate(ReadyFragment view, Bundle saveState) {
        super.onCreate(view, saveState);
        if (getView().getArguments() != null && getView().getArguments().containsKey("battle")) {
            mBattle = getView().getArguments().getParcelable("battle");
        }
    }

    @Override
    protected void onCreateView(ReadyFragment view) {
        super.onCreateView(view);
        if (mBattle != null) getView().setData(mBattle);
    }

    public void ready(int battleID) {
        BattleModel.getInstance().ready(battleID).unsafeSubscribe(new ServicesResponse<Boolean>() {
            @Override
            public void onNext(Boolean aBoolean) {
                EventBus.getDefault().post(mBattle);
            }
        });
    }

}
