package com.miguan.otk.module.battle;

import android.os.Bundle;

import com.dsk.chain.bijection.Presenter;
import com.miguan.otk.model.BattleModel;
import com.miguan.otk.model.bean.Battle;
import com.miguan.otk.model.services.ServicesResponse;
import com.sgun.utils.LUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (c) 2017/1/21. LiaoPeiKun Inc. All rights reserved.
 */

public class BattlingPresenter extends Presenter<BattlingFragment> {

    private Battle mBattle;

    @Override
    protected void onCreate(BattlingFragment view, Bundle saveState) {
        super.onCreate(view, saveState);
        if (getView().getArguments() != null && getView().getArguments().containsKey("battle")) {
            mBattle = getView().getArguments().getParcelable("battle");
        }
    }

    @Override
    protected void onCreateView(BattlingFragment view) {
        super.onCreateView(view);
        getView().setData(mBattle);
    }

    public void pick(List<Integer> list) {
        Map<String, Integer> map = new HashMap<>();
        for (int i=0; i<list.size(); i++) {
            map.put("car" + (i + 1), list.get(i) + 1);
            LUtils.log("index: " + list.get(i));
        }
        BattleModel.getInstance().pick(mBattle.getBattle_id(), map).unsafeSubscribe(new ServicesResponse<Boolean>() {
            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    getView().setPicked();
                    EventBus.getDefault().post(mBattle);
                }
            }
        });
    }

    public void ban(Integer ban) {
        BattleModel.getInstance().ban(mBattle.getBattle_id(), ban).unsafeSubscribe(new ServicesResponse<Boolean>() {
            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    getView().setBaned();
                    EventBus.getDefault().post(mBattle);
                }
            }
        });
    }

}