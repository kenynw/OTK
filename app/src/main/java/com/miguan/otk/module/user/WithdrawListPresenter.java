package com.miguan.otk.module.user;

import com.dsk.chain.expansion.list.BaseListActivityPresenter;
import com.miguan.otk.model.UserModel;
import com.miguan.otk.model.bean.Withdraw;
import com.miguan.otk.model.services.ServicesResponse;
import com.sgun.utils.LUtils;

/**
 * Copyright (c) 2016/12/16. LiaoPeiKun Inc. All rights reserved.
 */

public class WithdrawListPresenter extends BaseListActivityPresenter<WithdrawListActivity, Withdraw> {

    @Override
    protected void onCreateView(WithdrawListActivity view) {
        super.onCreateView(view);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        UserModel.getInstance().getWithdrawList()
                .doOnNext(withdraw -> getView().setData(withdraw))
                .map(Withdraw::getRecords)
                .unsafeSubscribe(getRefreshSubscriber());
    }

    @Override
    public void onLoadMore() {
        UserModel.getInstance().getWithdrawList(getCurPage()).unsafeSubscribe(getMoreSubscriber());
    }

    public void withdraw(Integer money) {
        UserModel.getInstance().withdraw(money).unsafeSubscribe(new ServicesResponse<Boolean>() {
            @Override
            public void onNext(Boolean aBoolean) {
                LUtils.toast("提现成功");
                onRefresh();
            }
        });
    }

}
