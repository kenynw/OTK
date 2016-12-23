package com.miguan.otk.module.user;

import com.dsk.chain.bijection.Presenter;
import com.miguan.otk.model.UserModel;
import com.miguan.otk.model.services.ServicesResponse;
import com.sgun.utils.LUtils;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
class RegisterPresenter extends Presenter<RegisterActivity> {


    void sendCaptcha(String mobile) {
//        UserModel.getInstance().sendCaptcha(mobile).subscribe(new ServicesResponse<Boolean>());
        LUtils.toast("发送中...");
    }

    void register(String mobile, String captcha, String password) {
        LUtils.toast("注册成功");
    }

}
