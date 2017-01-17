package com.miguan.otk.module.match;

import com.dsk.chain.expansion.list.BaseListActivityPresenter;
import com.miguan.otk.model.MatchModel;
import com.miguan.otk.model.bean.Mission;

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
        MatchModel.getInstance().getMissionList().unsafeSubscribe(getRefreshSubscriber());
    }


}
