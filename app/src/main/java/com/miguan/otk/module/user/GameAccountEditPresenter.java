package com.miguan.otk.module.user;

import com.dsk.chain.bijection.Presenter;
import com.sgun.utils.LUtils;

/**
 * Copyright (c) 2016/12/20. LiaoPeiKun Inc. All rights reserved.
 */

public class GameAccountEditPresenter extends Presenter {


    public void save(String name) {
        LUtils.toast("正在保存..." + name);
    }

}
