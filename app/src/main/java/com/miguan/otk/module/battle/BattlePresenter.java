package com.miguan.otk.module.battle;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.dsk.chain.expansion.data.BaseDataActivityPresenter;
import com.miguan.otk.model.BattleModel;
import com.miguan.otk.model.bean.Battle;
import com.miguan.otk.model.bean.Hero;
import com.miguan.otk.model.services.ServicesResponse;
import com.miguan.otk.utils.LUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (c) 2017/1/3. LiaoPeiKun Inc. All rights reserved.
 */

public class BattlePresenter extends BaseDataActivityPresenter<BattleActivity, Battle> implements BattleProxy {

    private int mBattleID;

    private Handler mHandler = new Handler();

    private Runnable mRunnable = this::refresh;

    private ServicesResponse<Battle> mSubscriber = new ServicesResponse<Battle>() {
        @Override
        public void onNext(Battle battle) {
            refresh();
        }
    };

    @Override
    protected void onCreate(BattleActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        EventBus.getDefault().register(this);
        mBattleID = getView().getIntent().getIntExtra("battle_id", 0);
    }

    @Override
    protected void onCreateView(BattleActivity view) {
        super.onCreateView(view);
        loadData(null);
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void loadData(Battle b) {
        BattleModel.getInstance().getBattleDetail(mBattleID)
                .doOnNext(battle -> {
                    if (battle.getIs_wait()) mHandler.postDelayed(mRunnable, 5000);
                })
                .unsafeSubscribe(new ServicesResponse<Battle>() {
                    @Override
                    public void onNext(Battle battle) {
                        getView().setData(battle);
                    }
                });
    }

    public void copyName(String name) {
        ClipboardManager cm = (ClipboardManager) (getView()).getSystemService(Context.CLIPBOARD_SERVICE);
        cm.setPrimaryClip(ClipData.newPlainText(null, name));
        LUtils.toast("复制成功");
    }

    public void toShot(Battle battle) {
        Intent intent = new Intent(getView(), ShotDetailActivity.class);
        intent.putExtra("battle", battle);
        getView().startActivity(intent);
    }

    @Override
    public void refresh() {
        BattleModel.getInstance().getBattleDetail(mBattleID)
                .doOnNext(battle -> {
                    if (battle.getIs_wait()) mHandler.postDelayed(mRunnable, 5000);
                })
                .unsafeSubscribe(new ServicesResponse<Battle>() {
                    @Override
                    public void onNext(Battle battle) {
                        getView().setData(battle);
                    }
                });
    }

    @Override
    public void ready() {
        BattleModel.getInstance().ready(mBattleID).unsafeSubscribe(new ServicesResponse<Battle>() {
            @Override
            public void onNext(Battle battle) {
                loadData(battle);
            }
        });
    }

    @Override
    public void pick(List<Hero> list) {
        Map<String, Integer> map = new HashMap<>();
        for (int i=0; i<list.size(); i++) {
            map.put("car" + (i + 1), list.get(i).getIndex());
        }
        BattleModel.getInstance().pick(mBattleID, map).unsafeSubscribe(mSubscriber);
    }

    @Override
    public void ban(Integer index) {
        BattleModel.getInstance().ban(mBattleID, index).unsafeSubscribe(mSubscriber);
    }

    @Override
    public void submitResult(int winnerID) {
        BattleModel.getInstance().submit(mBattleID, winnerID).unsafeSubscribe(mSubscriber);
    }

    @Override
    public void cancelResult() {
        BattleModel.getInstance().resubmit(mBattleID).unsafeSubscribe(mSubscriber);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        mHandler.removeCallbacks(mRunnable);
    }

}
