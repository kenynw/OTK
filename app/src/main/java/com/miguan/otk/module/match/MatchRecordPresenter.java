package com.miguan.otk.module.match;

import com.dsk.chain.expansion.list.BaseListFragmentPresenter;
import com.miguan.otk.model.MatchModel;
import com.miguan.otk.model.bean.Match;

/**
 * Copyright (c) 2016/12/1. LiaoPeiKun Inc. All rights reserved.
 */

public class MatchRecordPresenter extends BaseListFragmentPresenter<MatchRecordFragment, Match> {

    @Override
    protected void onCreateView(MatchRecordFragment view) {
        super.onCreateView(view);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        MatchModel.getInstance().getMyMatchList(1).unsafeSubscribe(getRefreshSubscriber());
    }

    @Override
    public void onLoadMore() {
        MatchModel.getInstance().getMyMatchList(getCurPage()).unsafeSubscribe(getMoreSubscriber());
    }
}
