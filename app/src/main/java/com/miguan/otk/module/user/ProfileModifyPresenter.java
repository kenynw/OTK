package com.miguan.otk.module.user;

import android.os.Bundle;

import com.dsk.chain.bijection.Presenter;
import com.miguan.otk.model.UserModel;
import com.miguan.otk.model.services.ServicesResponse;
import com.miguan.otk.utils.LUtils;


/**
 * Copyright (c) 2017/1/10. LiaoPeiKun Inc. All rights reserved.
 */

public class ProfileModifyPresenter extends Presenter<ProfileModifyActivity> {

    @Override
    protected void onCreate(ProfileModifyActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
    }

    public void submit(String key, String value) {
        UserModel.getInstance().setProfile(key, value)
                .unsafeSubscribe(new ServicesResponse<Boolean>() {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) LUtils.toast("修改成功");
                    }
                });
    }

}
