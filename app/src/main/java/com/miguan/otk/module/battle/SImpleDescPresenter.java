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

public class SimpleDescPresenter extends Presenter<SimpleDescFragment> {

    private Battle mBattle;

    @Override
    protected void onCreate(SimpleDescFragment view, Bundle saveState) {
        super.onCreate(view, saveState);
        if (getView().getArguments() != null && getView().getArguments().containsKey("battle")) {
            mBattle = getView().getArguments().getParcelable("battle");
        }
    }

    @Override
    protected void onCreateView(SimpleDescFragment view) {
        super.onCreateView(view);
        if (mBattle != null) getView().setData(mBattle);
    }

    public void ready(int battleID) {
        BattleModel.getInstance().ready(battleID).unsafeSubscribe(new ServicesResponse<Battle>() {
            @Override
            public void onNext(Battle aBoolean) {
                EventBus.getDefault().post(mBattle);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                EventBus.getDefault().post(mBattle);
            }
        });
    }

}
