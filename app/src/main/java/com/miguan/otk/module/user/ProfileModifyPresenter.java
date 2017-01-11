package com.miguan.otk.module.user;

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

    private int mType;

    @Override
    protected void onCreate(ProfileModifyActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mUser = view.getIntent().getParcelableExtra("user");
        mType = view.getIntent().getIntExtra("type", 0);
    }

    public void submit(String input) {
        switch (mType) {
            case 0 :
                UserModel.getInstance().setProfile(input, null, null, null, null, null, null).unsafeSubscribe(new ServicesResponse<>());
                break;
            case 1 :
                UserModel.getInstance().setProfile(null, input, null, null, null, null, null).unsafeSubscribe(new ServicesResponse<>());
                break;
            case 2 :
                UserModel.getInstance().setProfile(null, null, null, null, null, null, input).unsafeSubscribe(new ServicesResponse<>());
                break;
        }
    }

}
