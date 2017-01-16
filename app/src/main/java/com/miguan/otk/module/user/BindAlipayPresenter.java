package com.miguan.otk.module.user;

import com.dsk.chain.bijection.Presenter;
import com.miguan.otk.model.UserModel;
import com.miguan.otk.model.services.ServicesResponse;

/**
 * Copyright (c) 2017/1/16. LiaoPeiKun Inc. All rights reserved.
 */

public class BindAlipayPresenter extends Presenter {

    public void sendCaptcha(String mobile) {
        UserModel.getInstance().updateCaptcha(mobile).unsafeSubscribe(new ServicesResponse<>());
    }

    public void save(String mobile, String account, String captcha) {
        UserModel.getInstance().bindAlipay(mobile, account, captcha).unsafeSubscribe(new ServicesResponse<>());
    }

}
