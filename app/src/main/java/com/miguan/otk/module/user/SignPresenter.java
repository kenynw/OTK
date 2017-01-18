package com.miguan.otk.module.user;

import com.dsk.chain.expansion.data.BaseDataActivityPresenter;
import com.miguan.otk.model.UserModel;
import com.miguan.otk.model.bean.Sign;
import com.miguan.otk.model.services.ServicesResponse;

/**
 * Copyright (c) 2016/12/23. LiaoPeiKun Inc. All rights reserved.
 */

public class SignPresenter extends BaseDataActivityPresenter<SignActivity, Sign> {

    @Override
    protected void onCreateView(SignActivity view) {
        super.onCreateView(view);
        UserModel.getInstance().getSignInfo().unsafeSubscribe(getDataSubscriber());
    }

    public void sign() {
        UserModel.getInstance().sign().unsafeSubscribe(new ServicesResponse<>());
    }

}
