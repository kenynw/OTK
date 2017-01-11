package com.miguan.otk.module.main;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import com.dsk.chain.expansion.data.BaseDataFragmentPresenter;
import com.miguan.otk.model.UserModel;
import com.miguan.otk.model.bean.User;
import com.sgun.utils.LUtils;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */

public class MainMinePresenter extends BaseDataFragmentPresenter<MainMineFragment, User> {

    @Override
    protected void onCreateView(MainMineFragment view) {
        super.onCreateView(view);
        if (!TextUtils.isEmpty(LUtils.getPreferences().getString("token", ""))) UserModel.getInstance().userInfo().subscribe(getDataSubject());
    }

    @Override
    protected void onResult(int requestCode, int resultCode, Intent data) {
        super.onResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 1) {
            if (data != null && data.getParcelableExtra("user") != null) {
                publishData(data.getParcelableExtra("user"));
            }
        }
    }
}
