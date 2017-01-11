package com.miguan.otk.module.user;

import android.content.Intent;
import android.os.Bundle;

import com.dsk.chain.expansion.data.BaseDataActivityPresenter;
import com.miguan.otk.model.UserModel;
import com.miguan.otk.model.bean.User;

/**
 * Copyright (c) 2016/11/28. LiaoPeiKun Inc. All rights reserved.
 */

public class ProfilePresenter extends BaseDataActivityPresenter<ProfileActivity, User> {

    private User mUser;

    @Override
    protected void onCreate(ProfileActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mUser = view.getIntent().getParcelableExtra("user");
    }

    @Override
    protected void onCreateView(ProfileActivity view) {
        super.onCreateView(view);
        if (mUser != null) publishObject(mUser);
        UserModel.getInstance().getProfile().unsafeSubscribe(getDataSubscriber());
    }

    public void toModify(User user, int type) {
        Intent i = new Intent(getView(), ProfileModifyActivity.class);
        i.putExtra("type", type);
        i.putExtra("user", user);
        getView().startActivity(i);
    }

}
