package com.miguan.otk.module.store;

import com.dsk.chain.expansion.list.BaseListFragmentPresenter;
import com.miguan.otk.model.bean.Goods;
import com.miguan.otk.model.bean.Order;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Copyright (c) 2016/12/19. LiaoPeiKun Inc. All rights reserved.
 */
public class OrderListPresenter extends BaseListFragmentPresenter<OrderListFragment, Order> {

    @Override
    protected void onCreateView(OrderListFragment view) {
        super.onCreateView(view);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        List<Order> list = new ArrayList<>();
        for (int i=0; i<10; i++) {
            Order order = new Order();
            order.setOrder_num(i + "574676765374564");
            order.setState(i%2==0 ? "审核中" : "已完成");

            Goods goods = new Goods();
            order.setSpec("薄荷绿" + i);
            goods.setGoods_name(i + "萌萌哒创意小耳机萌萌哒创意小耳机萌萌哒");
            goods.setGoods_price("4000" + i);
            goods.setGoods_image_url("http://static.otkpk.com/uploads/article/1481871059679989.jpg");
            order.setGoods(goods);
            list.add(order);
        }
        Observable.just(list).unsafeSubscribe(getRefreshSubscriber());
    }
}
