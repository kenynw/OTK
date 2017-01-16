package com.miguan.otk.module.user;

import com.dsk.chain.expansion.list.BaseListActivityPresenter;
import com.miguan.otk.model.UserModel;
import com.miguan.otk.model.bean.Game;

/**
 * Copyright (c) 2016/12/20. LiaoPeiKun Inc. All rights reserved.
 */

public class GameAccountPresenter extends BaseListActivityPresenter<GameAccountActivity, Game> {

    @Override
    protected void onCreateView(GameAccountActivity view) {
        super.onCreateView(view);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        UserModel.getInstance().getGameAccounts().unsafeSubscribe(getRefreshSubscriber());
    }

}
