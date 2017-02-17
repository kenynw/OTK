package com.miguan.otk.module.settings;

import android.app.Activity;

import com.dsk.chain.bijection.Presenter;
import com.miguan.otk.model.CommonModel;
import com.miguan.otk.utils.DataCleanManager;
import com.sgun.utils.LUtils;

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
        LUtils.getPreferences().edit().putString("token", "").apply();
        getView().setResult(Activity.RESULT_OK);
        getView().finish();
    }

    public void checkUpdate() {
        CommonModel.getInstance().checkUpdate(getView());
    }

}
