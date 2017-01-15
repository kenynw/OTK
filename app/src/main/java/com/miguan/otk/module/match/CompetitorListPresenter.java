package com.miguan.otk.module.match;

import android.os.Bundle;

import com.dsk.chain.expansion.list.BaseListActivityPresenter;
import com.miguan.otk.R;
import com.miguan.otk.model.MatchModel;
import com.miguan.otk.model.bean.User;

/**
 * Copyright (c) 2016/12/22. LiaoPeiKun Inc. All rights reserved.
 */

class CompetitorListPresenter extends BaseListActivityPresenter<CompetitorListActivity, User> {

    private int mCompetitionID;

    private int mType;

    @Override
    protected void onCreate(CompetitorListActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mCompetitionID = getView().getIntent().getIntExtra("match_id", 0);
        mType = getView().getIntent().getIntExtra("type", 1);
    }

    @Override
    protected void onCreateView(CompetitorListActivity view) {
        super.onCreateView(view);
        if (mType == 1) getView().setToolbarTitle(R.string.title_activity_enroll);
        else getView().setToolbarTitle(R.string.title_activity_bench);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        MatchModel.getInstance().getCompetitors(mCompetitionID, mType).unsafeSubscribe(getRefreshSubscriber());
    }

}
