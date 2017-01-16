package com.miguan.otk.module.user;

import com.dsk.chain.bijection.Presenter;
import com.miguan.otk.model.UserModel;
import com.miguan.otk.model.services.ServicesResponse;
import com.sgun.utils.LUtils;

/**
 * Copyright (c) 2016/12/20. LiaoPeiKun Inc. All rights reserved.
 */

public class GameAccountAddPresenter extends Presenter<GameAccountAddActivity> {

    public void save(String gameName, String accountName) {
        UserModel.getInstance().addGameAccount(gameName, accountName).unsafeSubscribe(new ServicesResponse<Boolean>() {
            @Override
            public void onNext(Boolean o) {
                LUtils.toast("添加成功");
            }
        });
    }

}
