package com.miguan.otk.module.battle;

import com.dsk.chain.bijection.Presenter;
import com.miguan.otk.model.BattleModel;
import com.miguan.otk.model.services.ServicesResponse;

/**
 * Copyright (c) 2017/1/21. LiaoPeiKun Inc. All rights reserved.
 */

public class ReadyPresenter extends Presenter<ReadyFragment> {

    public void ready(int battleID) {
        BattleModel.getInstance().ready(battleID).unsafeSubscribe(new ServicesResponse<>());
    }

}
