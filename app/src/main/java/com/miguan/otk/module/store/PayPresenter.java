package com.miguan.otk.module.store;

import android.app.Activity;
import android.content.Intent;

import com.dsk.chain.expansion.data.BaseDataActivityPresenter;
import com.miguan.otk.model.bean.Address;
import com.miguan.otk.model.bean.Goods;
import com.miguan.otk.model.bean.Order;

import rx.Observable;

/**
 * Copyright (c) 2016/12/19. LiaoPeiKun Inc. All rights reserved.
 */
public class PayPresenter extends BaseDataActivityPresenter<PayActivity, Order> {

    @Override
    protected void onCreateView(PayActivity view) {
        super.onCreateView(view);
        Order order = new Order();
        Goods goods = new Goods();
        goods.setGoods_name("萌萌哒创意小耳机萌萌哒创意小耳机萌萌哒");
        goods.setGoods_price("6000");
        goods.setGoods_image_url("http://www.otkpk.com/article/index/116.html");
        order.setSpec("薄荷绿");
        order.setGoods(goods);

        Address address = new Address();
        address.setTrue_name("廖培坤1");
        address.setAddress("福建省  厦门市  思明区望海路16号之一301米冠网络1");
        address.setMob_phone("15375870891");
        order.setAddress(address);

        Observable.just(order).subscribe(getDataSubscriber());
    }

    @Override
    protected void onResult(int requestCode, int resultCode, Intent data) {
        super.onResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 0) {
            Address address = data.getParcelableExtra("address");
            if (address != null) getView().setAddress(address);
        }
    }
}
