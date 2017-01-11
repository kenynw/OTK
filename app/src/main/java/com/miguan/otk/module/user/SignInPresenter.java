package com.miguan.otk.module.user;

import com.dsk.chain.expansion.data.BaseDataActivityPresenter;
import com.miguan.otk.model.UserModel;
import com.miguan.otk.model.bean.Sign;
import com.miguan.otk.model.services.ServicesResponse;

/**
 * Copyright (c) 2016/12/23. LiaoPeiKun Inc. All rights reserved.
 */

public class SignInPresenter extends BaseDataActivityPresenter<SignInActivity, Sign> {

    @Override
    protected void onCreateView(SignInActivity view) {
        super.onCreateView(view);
        UserModel.getInstance().getSignDetail().unsafeSubscribe(getDataSubscriber());
    }

    public void sign() {
        UserModel.getInstance().sign().unsafeSubscribe(new ServicesResponse<>());
    }

}
