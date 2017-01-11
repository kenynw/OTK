package com.miguan.otk.model;

import com.dsk.chain.model.AbsModel;
import com.miguan.otk.model.bean.Against;
import com.miguan.otk.model.bean.Match;
import com.miguan.otk.model.services.DefaultTransform;
import com.miguan.otk.model.services.ServicesClient;
import com.sgun.utils.LUtils;

import java.util.List;

import rx.Observable;

/**
 * Copyright (c) 2017/1/6. LiaoPeiKun Inc. All rights reserved.
 */

public class MatchModel extends AbsModel {

    public static MatchModel getInstance() {
        return getInstance(MatchModel.class);
    }

    /**
     * 我的参赛列表
     * @param page 当前页数
     * @return
     */
    public Observable<List<Match>> getMyMatchList(int page) {
        return ServicesClient.getServices().myMatchList(LUtils.getPreferences().getString("token", ""), page).compose(new DefaultTransform<>());
    }

    /**
     * 我的对战记录
     * @param page 当前页数
     * @return
     */
    public Observable<List<Against>> getAgainstList(int page) {
        return ServicesClient.getServices().againstList(LUtils.getPreferences().getString("token", ""), page).compose(new DefaultTransform<>());
    }

}
