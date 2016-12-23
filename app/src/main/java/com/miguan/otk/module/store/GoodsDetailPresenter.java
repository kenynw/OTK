package com.miguan.otk.module.store;

import android.os.Bundle;

import com.dsk.chain.expansion.data.BaseDataActivityPresenter;
import com.miguan.otk.model.bean.Goods;

import rx.Observable;

/**
 * Copyright (c) 2016/12/16. LiaoPeiKun Inc. All rights reserved.
 */
class GoodsDetailPresenter extends BaseDataActivityPresenter<GoodsDetailActivity, Goods> {

    private Goods mGoods;

    private int mGoodsID;

    @Override
    protected void onCreate(GoodsDetailActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mGoods = getView().getIntent().getParcelableExtra("goods");
        mGoodsID = getView().getIntent().getIntExtra("goods_id", 0);
    }

    @Override
    protected void onCreateView(GoodsDetailActivity view) {
        super.onCreateView(view);
        Observable.just(mGoods).unsafeSubscribe(getDataSubscriber());
    }
}
