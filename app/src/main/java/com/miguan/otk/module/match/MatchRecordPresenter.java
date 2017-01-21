package com.miguan.otk.module.match;

import com.dsk.chain.expansion.list.BaseListFragmentPresenter;
import com.miguan.otk.model.BattleModel;
import com.miguan.otk.model.bean.Battle;

/**
 * Copyright (c) 2016/12/20. LiaoPeiKun Inc. All rights reserved.
 */

public class MatchRecordPresenter extends BaseListFragmentPresenter<MatchRecordFragment, Battle> {

    @Override
    protected void onCreateView(MatchRecordFragment view) {
        super.onCreateView(view);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        BattleModel.getInstance().getBattleList(1).unsafeSubscribe(getRefreshSubscriber());
    }

    @Override
    public void onLoadMore() {
        BattleModel.getInstance().getBattleList(getCurPage()).unsafeSubscribe(getMoreSubscriber());
    }
}
