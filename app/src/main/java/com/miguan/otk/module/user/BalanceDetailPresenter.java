package com.miguan.otk.module.user;

import com.dsk.chain.expansion.data.BaseDataActivityPresenter;
import com.miguan.otk.model.bean.User;

/**
 * Copyright (c) 2016/12/16. LiaoPeiKun Inc. All rights reserved.
 */

public class BalanceDetailPresenter extends BaseDataActivityPresenter<BalanceDetailActivity, User> {

    @Override
    protected void onCreateView(BalanceDetailActivity view) {
        super.onCreateView(view);
        if (getView().getIntent().getParcelableExtra("user") != null) publishObject(getView().getIntent().getParcelableExtra("user"));
    }
}
