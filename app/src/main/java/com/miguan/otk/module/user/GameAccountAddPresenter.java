package com.miguan.otk.module.user;

import com.dsk.chain.bijection.Presenter;
import com.sgun.utils.LUtils;

/**
 * Copyright (c) 2016/12/20. LiaoPeiKun Inc. All rights reserved.
 */

public class GameAccountAddPresenter extends Presenter<GameAccountAddActivity> {

    public void save(String gameName, String accountName) {
        LUtils.toast("保存中...");
    }

}
