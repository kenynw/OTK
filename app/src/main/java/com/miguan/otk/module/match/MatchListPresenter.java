package com.miguan.otk.module.match;

import android.os.Bundle;

import com.dsk.chain.expansion.list.BaseListFragmentPresenter;
import com.miguan.otk.model.bean.Match;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Copyright (c) 2016/12/1. LiaoPeiKun Inc. All rights reserved.
 */

public class MatchListPresenter extends BaseListFragmentPresenter<MatchListFragment, Match> {

    private int mType;

    public static MatchListFragment getFragment(int type) {
        MatchListFragment fragment = new MatchListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void onCreate(MatchListFragment view, Bundle saveState) {
        super.onCreate(view, saveState);
        if (getView().getArguments() != null) {
            Bundle bundle = getView().getArguments();
            mType = bundle.getInt("type", 0);
        }
    }

    @Override
    protected void onCreateView(MatchListFragment view) {
        super.onCreateView(view);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        List<Match> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Match match = new Match();
            match.setTitle("炉石传说——OTK电竞常规赛#" + mType);
            match.setTime("11月24日 20:30");
            match.setEnrolled("1/32(0)7");
            match.setRules("BO3");
            list.add(match);
        }
        Observable.just(list).unsafeSubscribe(getRefreshSubscriber());
    }
}
