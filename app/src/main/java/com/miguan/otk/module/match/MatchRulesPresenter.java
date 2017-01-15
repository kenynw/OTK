package com.miguan.otk.module.match;

import android.os.Bundle;

import com.dsk.chain.expansion.data.BaseDataFragmentPresenter;
import com.miguan.otk.model.MatchModel;
import com.miguan.otk.model.bean.Match;

/**
 * Copyright (c) 2016/11/25. LiaoPeiKun Inc. All rights reserved.
 */
public class MatchRulesPresenter extends BaseDataFragmentPresenter<MatchRulesFragment, Match> {

    private int mMatchID = 0;

    @Override
    protected void onCreate(MatchRulesFragment view, Bundle saveState) {
        super.onCreate(view, saveState);
        if (getView().getArguments() != null) {
            mMatchID = getView().getArguments().getInt("match_id");
        }
    }

    @Override
    protected void onCreateView(MatchRulesFragment view) {
        super.onCreateView(view);
        MatchModel.getInstance().getCompetitionRule(mMatchID).unsafeSubscribe(getSubscriber());
    }
}
