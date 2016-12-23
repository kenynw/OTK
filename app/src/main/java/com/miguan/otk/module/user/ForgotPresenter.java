package com.miguan.otk.module.user;

import com.dsk.chain.bijection.Presenter;
import com.miguan.otk.model.UserModel;
import com.miguan.otk.model.services.ServicesResponse;
import com.sgun.utils.LUtils;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
class ForgotPresenter extends Presenter<ForgotActivity> {


    void sendCaptcha(String mobile) {
        UserModel.getInstance().sendCaptcha(mobile).subscribe(new ServicesResponse());
        LUtils.toast("正在发送验证码...");
    }

    void changePwd(String mobile, String newPwd, String captcha) {
        LUtils.toast("更改成功...");
    }

}
