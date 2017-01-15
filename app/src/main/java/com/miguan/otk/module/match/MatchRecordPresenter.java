package com.miguan.otk.module.match;

import com.dsk.chain.expansion.list.BaseListFragmentPresenter;
import com.miguan.otk.model.MatchModel;
import com.miguan.otk.model.bean.Schedule;

/**
 * Copyright (c) 2016/12/20. LiaoPeiKun Inc. All rights reserved.
 */

public class MatchRecordPresenter extends BaseListFragmentPresenter<MatchRecordFragment, Schedule> {

    @Override
    protected void onCreateView(MatchRecordFragment view) {
        super.onCreateView(view);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        MatchModel.getInstance().getAgainstList(1).unsafeSubscribe(getRefreshSubscriber());
    }

    @Override
    public void onLoadMore() {
        MatchModel.getInstance().getAgainstList(getCurPage()).unsafeSubscribe(getMoreSubscriber());
    }
}
