package com.miguan.otk.model;

import com.dsk.chain.model.AbsModel;
import com.miguan.otk.model.bean.Battle;
import com.miguan.otk.model.services.DefaultTransform;
import com.miguan.otk.model.services.ServicesClient;
import com.sgun.utils.LUtils;

import java.util.List;

import rx.Observable;

/**
 * Copyright (c) 2017/1/21. LiaoPeiKun Inc. All rights reserved.
 */

public class BattleModel extends AbsModel {

    public static BattleModel getInstance() {
        return getInstance(BattleModel.class);
    }

    /**
     * 我的对战记录
     * @param page 当前页数
     * @return
     */
    public Observable<List<Battle>> getBattleList(int page) {
        return ServicesClient.getServices().againstList(LUtils.getPreferences().getString("token", ""), page).compose(new DefaultTransform<>());
    }

    public Observable<Battle> getBattleDetail(int battleID) {
        return ServicesClient.getServices().battleDetail(LUtils.getPreferences().getString("token", ""), battleID).compose(new DefaultTransform<>());
    }

    /**
     * 对战准备
     * @param matchID 赛事ID
     * @return
     */
    public Observable<Boolean> ready(int matchID) {
        return ServicesClient.getServices().ready(LUtils.getPreferences().getString("token", ""), matchID).compose(new DefaultTransform<>());
    }

}
