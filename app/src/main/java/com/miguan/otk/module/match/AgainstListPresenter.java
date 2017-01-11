package com.miguan.otk.module.match;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.dsk.chain.expansion.list.BaseListFragmentPresenter;
import com.miguan.otk.model.MatchModel;
import com.miguan.otk.model.bean.Against;

/**
 * Copyright (c) 2016/12/22. LiaoPeiKun Inc. All rights reserved.
 */

public class AgainstListPresenter extends BaseListFragmentPresenter<AgainstListFragment, Against> {

    private int mRound = 0;

    @Override
    protected void onCreate(AgainstListFragment view, Bundle saveState) {
        super.onCreate(view, saveState);
        if (getView().getArguments() != null) {
            Bundle bundle = getView().getArguments();
            mRound = bundle.getInt("round", 0);
        }
    }

    @Override
    protected void onCreateView(@NonNull AgainstListFragment view) {
        super.onCreateView(view);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        MatchModel.getInstance().getAgainstList(mRound).unsafeSubscribe(getRefreshSubscriber());
    }

    @Override
    public void onLoadMore() {
        MatchModel.getInstance().getAgainstList(mRound++).unsafeSubscribe(getMoreSubscriber());
    }

    public void changeRound(int result) {
        mRound = mRound + result;
        onRefresh();
    }

}
