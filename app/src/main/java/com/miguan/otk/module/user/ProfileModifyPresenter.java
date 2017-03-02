package com.miguan.otk.module.user;

import android.app.Activity;
import android.os.Bundle;

import com.dsk.chain.bijection.Presenter;
import com.miguan.otk.model.UserModel;
import com.miguan.otk.model.bean.User;
import com.miguan.otk.model.services.ServicesResponse;


/**
 * Copyright (c) 2017/1/10. LiaoPeiKun Inc. All rights reserved.
 */

public class ProfileModifyPresenter extends Presenter<ProfileModifyActivity> {

    private User mUser;

    @Override
    protected void onCreate(ProfileModifyActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mUser = getView().getIntent().getParcelableExtra("user");
    }

    public void submit(String key, String value) {
        UserModel.getInstance().setProfile(key, value)
                .unsafeSubscribe(new ServicesResponse<Boolean>() {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            getView().setResult(Activity.RESULT_OK);
                            getView().finish();
                        }
                    }
                });
    }

}
