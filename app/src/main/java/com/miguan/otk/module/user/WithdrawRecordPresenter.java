package com.miguan.otk.module.user;

import com.dsk.chain.expansion.data.BaseDataActivityPresenter;
import com.miguan.otk.model.UserModel;
import com.miguan.otk.model.bean.Withdraw;

/**
 * Copyright (c) 2016/12/16. LiaoPeiKun Inc. All rights reserved.
 */

public class WithdrawRecordPresenter extends BaseDataActivityPresenter<WithdrawRecordActivity, Withdraw> {

    @Override
    protected void onCreateView(WithdrawRecordActivity view) {
        super.onCreateView(view);
        UserModel.getInstance().getWithdrawRecord(1).unsafeSubscribe(getDataSubscriber());
    }
}
