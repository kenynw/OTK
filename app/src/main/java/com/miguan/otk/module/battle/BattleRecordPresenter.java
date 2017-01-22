package com.miguan.otk.module.battle;

import com.dsk.chain.expansion.list.BaseListFragmentPresenter;
import com.miguan.otk.model.BattleModel;
import com.miguan.otk.model.bean.Battle;

/**
 * Copyright (c) 2016/12/20. LiaoPeiKun Inc. All rights reserved.
 */

public class BattleRecordPresenter extends BaseListFragmentPresenter<BattleRecordFragment, Battle> {

    @Override
    protected void onCreateView(BattleRecordFragment view) {
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
