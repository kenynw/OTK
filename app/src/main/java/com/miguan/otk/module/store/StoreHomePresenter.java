package com.miguan.otk.module.store;

import com.dsk.chain.expansion.list.BaseListActivityPresenter;
import com.miguan.otk.model.bean.Goods;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Copyright (c) 2016/12/16. LiaoPeiKun Inc. All rights reserved.
 */

public class StoreHomePresenter extends BaseListActivityPresenter<StoreHomeActivity, Goods> {

    @Override
    protected void onCreateView(StoreHomeActivity view) {
        super.onCreateView(view);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        List<Goods> list = new ArrayList<>();
        for (int i=0; i<10; i++) {
            Goods goods = new Goods();
            goods.setGoods_name("战队专属鼠标垫TK战队");
            goods.setGoods_image_url("http://ww2.sinaimg.cn/mw690/73c91825jw1f93ielr68bj20en0b41fc.jpg");
            goods.setGoods_price("￥128.0");
            goods.setGoods_spec_list(new String[] {"土鳖金", "初恋粉", "分手黄"});
            list.add(goods);
        }
        Observable.just(list).unsafeSubscribe(getRefreshSubscriber());
    }
}
