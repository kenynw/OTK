package com.miguan.otk.module.match;

import com.dsk.chain.expansion.list.BaseListActivityPresenter;
import com.miguan.otk.model.bean.Mission;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Copyright (c) 2016/12/23. LiaoPeiKun Inc. All rights reserved.
 */

public class MissionListPresenter extends BaseListActivityPresenter<MissionListActivity, Mission> {

    @Override
    protected void onCreateView(MissionListActivity view) {
        super.onCreateView(view);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        List<Mission> list = new ArrayList<>();
        for (int i=0; i<10; i++) {
            Mission mission = new Mission();
            mission.setMission_name("今日签到");
            mission.setMission_desc("每日一签、撒币多多");
            mission.setMission_image("http://oss.otkpk.com/uploads/game-name/1481617456702582.png");
            mission.setMission_bonus("25000撒币");
            list.add(mission);
        }
        Observable.just(list).unsafeSubscribe(getRefreshSubscriber());
    }
}
