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
        UserModel.getInstance().forgotCaptcha(mobile).subscribe(new ServicesResponse<Boolean>());
    }

    void changePwd(String mobile, String captcha, String newPwd) {
        UserModel.getInstance().modifyPwd(mobile, captcha, newPwd).subscribe(new ServicesResponse<Boolean>() {
            @Override
            public void onNext(Boolean aBoolean) {
                LUtils.getPreferences().edit().putString("token", "").apply();
                getView().finish();
            }
        });
    }

}
