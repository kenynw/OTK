package com.miguan.otk.module.settings;

import com.dsk.chain.bijection.Presenter;
import com.sgun.utils.LUtils;

/**
 * Copyright (c) 2016/12/21. LiaoPeiKun Inc. All rights reserved.
 */

public class SettingsPresenter extends Presenter<SettingsActivity> {

    public void clearCache() {
        LUtils.toast("success");
    }

    public void logout() {
        LUtils.getPreferences().edit().putString("token", "").apply();
        getView().finish();
    }

}
