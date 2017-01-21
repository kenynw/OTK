package com.miguan.otk.module.match;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.dsk.chain.expansion.list.BaseListFragmentPresenter;
import com.miguan.otk.model.MatchModel;
import com.miguan.otk.model.bean.Battle;

/**
 * Copyright (c) 2016/12/22. LiaoPeiKun Inc. All rights reserved.
 */

public class ScheduleListPresenter extends BaseListFragmentPresenter<ScheduleListFragment, Battle> {

    private int mMatchID = 0;

    private int mRound = 0;

    @Override
    protected void onCreate(ScheduleListFragment view, Bundle saveState) {
        super.onCreate(view, saveState);
        if (getView().getArguments() != null) {
            Bundle bundle = getView().getArguments();
            mMatchID = bundle.getInt("match_id");
            mRound = bundle.getInt("round");
        }
    }

    @Override
    protected void onCreateView(@NonNull ScheduleListFragment view) {
        super.onCreateView(view);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        MatchModel.getInstance().getCompetitionSchedule(mMatchID, mRound)
                .doOnNext(schedule -> getView().setData(schedule))
                .map(Battle::getBattles)
                .unsafeSubscribe(getRefreshSubscriber());
    }

    public void changeRound(int result) {
        mRound = mRound + result;
        onRefresh();
    }

}
