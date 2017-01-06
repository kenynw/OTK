package com.miguan.otk.model;

import com.dsk.chain.model.AbsModel;
import com.miguan.otk.model.bean.VS;
import com.miguan.otk.model.services.DefaultTransform;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Copyright (c) 2017/1/6. LiaoPeiKun Inc. All rights reserved.
 */

public class MatchModel extends AbsModel {

    public static MatchModel getInstance() {
        return getInstance(MatchModel.class);
    }

    public Observable<List<VS>> getVSList(int round) {
        List<VS> list = new ArrayList<>();
        if (round < 4) {
            for (int i = 0; i < 30; i++) {
                VS vs = new VS();
                vs.setPlayer_a("第" + round + "轮");
                vs.setPlayer_b("机智的黄图哥");
                list.add(vs);
            }
        }
        return Observable.just(list).compose(new DefaultTransform<>());
    }

}
