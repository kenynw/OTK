package com.miguan.otk.module.settings;

import android.app.Activity;

import com.dsk.chain.bijection.Presenter;
import com.miguan.otk.config.UserPreferences;
import com.miguan.otk.model.CommonModel;
import com.miguan.otk.utils.DataCleanManager;
import com.miguan.otk.utils.LUtils;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.auth.AuthService;


/**
 * Copyright (c) 2016/12/21. LiaoPeiKun Inc. All rights reserved.
 */

public class SettingsPresenter extends Presenter<SettingsActivity> {

    @Override
    protected void onCreateView(SettingsActivity view) {
        super.onCreateView(view);
        getData();
    }

    private void getData() {
        getView().setData(DataCleanManager.getCacheSize(getView()), LUtils.getAppVersionName());
    }

    public void clearCache() {
        DataCleanManager.cleanInternalCache(getView());
        LUtils.toast("清除成功");
        getData();
    }

    public void logout() {
        NIMClient.getService(AuthService.class).logout();
        UserPreferences.setToken("");
        UserPreferences.setNIMToken("");
        UserPreferences.setUserID("");
        getView().setResult(Activity.RESULT_OK);
        getView().finish();
    }

    public void checkUpdate() {
        CommonModel.getInstance().checkUpdate(getView());
    }

}
