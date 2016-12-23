package com.miguan.otk.module.user;

import com.dsk.chain.expansion.list.BaseListFragmentPresenter;
import com.miguan.otk.model.bean.Balance;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Copyright (c) 2016/12/16. LiaoPeiKun Inc. All rights reserved.
 */

public class BalanceListPresenter extends BaseListFragmentPresenter<BalanceListFragment, Balance> {

    @Override
    protected void onCreateView(BalanceListFragment view) {
        super.onCreateView(view);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        List<Balance> list = new ArrayList<>();
        for (int i=0; i<10; i++) {
            Balance balance = new Balance();
            balance.setTitle("签到奖励100撒币");
            balance.setDetail("+100");
            balance.setDate("2016-11-13");
            balance.setBalance("撒币余额2259");
            list.add(balance);
        }
        Observable.just(list).unsafeSubscribe(getRefreshSubscriber());
    }
}
