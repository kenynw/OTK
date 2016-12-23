package com.miguan.otk.module.match;

import com.dsk.chain.expansion.list.BaseListFragmentPresenter;
import com.miguan.otk.model.bean.Match;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Copyright (c) 2016/12/20. LiaoPeiKun Inc. All rights reserved.
 */

public class MatchRecordPresenter extends BaseListFragmentPresenter<MatchRecordFragment, Match> {

    @Override
    protected void onCreateView(MatchRecordFragment view) {
        super.onCreateView(view);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        List<Match> list = new ArrayList<>();
        for (int i=0; i<10; i++) {
            Match match = new Match();
            match.setTitle(i + "OTK炉石传说常规赛A（联系群12346586841）");
            match.setNature("常规");
            match.setMatch_id(333);
            list.add(match);
        }
        Observable.just(list).unsafeSubscribe(getRefreshSubscriber());
    }
}
