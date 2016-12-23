package com.miguan.otk.module.store;

import android.os.Bundle;

import com.dsk.chain.expansion.data.BaseDataActivityPresenter;
import com.miguan.otk.model.bean.Order;

/**
 * Copyright (c) 2016/12/20. LiaoPeiKun Inc. All rights reserved.
 */

public class OrderDetailPresenter extends BaseDataActivityPresenter<OrderDetailActivity, Order> {

    private int mOrderID;

    @Override
    protected void onCreate(OrderDetailActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mOrderID = getView().getIntent().getIntExtra("order_id", 0);
    }

    @Override
    protected void onCreateView(OrderDetailActivity view) {
        super.onCreateView(view);
        setData();
    }

    private void setData() {
        Order order = new Order();
        order.setState("审核中");
        order.setOrder_num("");
    }

}
