package com.miguan.otk.module.user;

import com.dsk.chain.bijection.Presenter;
import com.miguan.otk.model.UserModel;
import com.miguan.otk.model.bean.User;
import com.miguan.otk.model.services.ServicesResponse;
import com.sgun.utils.LUtils;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
class RegisterPresenter extends Presenter<RegisterActivity> {

    void sendCaptcha(String mobile) {
        UserModel.getInstance().registerCaptcha(mobile).subscribe(new ServicesResponse<Boolean>() {
            @Override
            public void onNext(Boolean result) {
                if (result) LUtils.toast("发送成功");
            }
        });
    }

    void register(String username, String mobile, String captcha, String password) {
        UserModel.getInstance().register(username, mobile, captcha, password).subscribe(new ServicesResponse<User>() {
            @Override
            public void onNext(User user) {
                LUtils.getPreferences().edit().putString("token", user.getToken()).apply();
                getView().finish();
            }
        });
    }

}
