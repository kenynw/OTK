package com.miguan.otk.module.battle;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.dsk.chain.bijection.Presenter;
import com.miguan.otk.model.BattleModel;
import com.miguan.otk.model.bean.Battle;
import com.miguan.otk.model.bean.Hero;
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

    private ServicesResponse<Battle> mSubscriber = new ServicesResponse<Battle>() {
        @Override
        public void onNext(Battle aBoolean) {
            EventBus.getDefault().post(mBattle);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            EventBus.getDefault().post(mBattle);
        }
    };

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

    public void pick(List<Hero> list) {
        Map<String, Integer> map = new HashMap<>();
        for (int i=0; i<list.size(); i++) {
            map.put("car" + (i + 1), list.get(i).getIndex());
        }
        BattleModel.getInstance().pick(mBattle.getBattle_id(), map).unsafeSubscribe(mSubscriber);
    }

    public void ban(Integer ban) {
        BattleModel.getInstance().ban(mBattle.getBattle_id(), ban).unsafeSubscribe(mSubscriber);
    }

    void submit(Integer winnerID) {
        BattleModel.getInstance().submit(mBattle.getBattle_id(), winnerID).unsafeSubscribe(mSubscriber);
    }

    public void resubmit() {
        BattleModel.getInstance().resubmit(mBattle.getBattle_id()).unsafeSubscribe(mSubscriber);
    }

    public void copyName(String name) {
        ClipboardManager cm = (ClipboardManager) (getView().getActivity()).getSystemService(Context.CLIPBOARD_SERVICE);
        cm.setPrimaryClip(ClipData.newPlainText(null, name));
        LUtils.toast("复制成功");
    }

    public void toShot() {
        Intent intent = new Intent(getView().getActivity(), ShotDetailActivity.class);
        intent.putExtra("battle", mBattle);
        getView().startActivity(intent);
    }

}