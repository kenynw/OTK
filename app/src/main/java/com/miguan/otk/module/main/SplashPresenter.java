package com.miguan.otk.module.main;

import com.dsk.chain.expansion.data.BaseDataActivityPresenter;
import com.miguan.otk.model.CommonModel;
import com.miguan.otk.model.bean.Splash;

/**
 * Copyright (c) 2017/1/13. LiaoPeiKun Inc. All rights reserved.
 */

public class SplashPresenter extends BaseDataActivityPresenter<SplashActivity, Splash> {

    @Override
    protected void onCreateView(SplashActivity view) {
        super.onCreateView(view);
        CommonModel.getInstance().getSplashImage("read-splash", 1).unsafeSubscribe(getDataSubscriber());
    }

}
