package com.miguan.otk.module.user;

import android.os.Bundle;

import com.dsk.chain.expansion.list.BaseListFragmentPresenter;
import com.miguan.otk.model.UserModel;
import com.miguan.otk.model.bean.Balance;

/**
 * Copyright (c) 2016/12/16. LiaoPeiKun Inc. All rights reserved.
 */

public class BalanceListPresenter extends BaseListFragmentPresenter<BalanceListFragment, Balance> {

    public static final String BALANCE_SCORE = "socre";

    public static final String BALANCE_MONEY = "money";

    private String mType;

    @Override
    protected void onCreate(BalanceListFragment view, Bundle saveState) {
        super.onCreate(view, saveState);
        if (getView().getArguments() != null) {
            mType = getView().getArguments().getString("type", BALANCE_SCORE);
        }
    }

    @Override
    protected void onCreateView(BalanceListFragment view) {
        super.onCreateView(view);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        UserModel.getInstance().getBalanceList(mType, 1).unsafeSubscribe(getRefreshSubscriber());
    }

    @Override
    public void onLoadMore() {
        UserModel.getInstance().getBalanceList(mType, getCurPage()).unsafeSubscribe(getMoreSubscriber());
    }
}
