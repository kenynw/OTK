package com.miguan.otk.model;

import com.dsk.chain.model.AbsModel;
import com.miguan.otk.model.bean.News;
import com.miguan.otk.model.services.DefaultTransform;
import com.miguan.otk.model.services.ServicesClient;

import java.util.List;

import rx.Observable;

/**
 * Copyright (c) 2017/1/10. LiaoPeiKun Inc. All rights reserved.
 */

public class NewsModel extends AbsModel {

    public static NewsModel getInstance() {
        return getInstance(NewsModel.class);
    }

    public Observable<List<News>> getNewsList(int type, int page) {
        return ServicesClient.getServices().newsList(type, page).compose(new DefaultTransform<>());
    }

}
