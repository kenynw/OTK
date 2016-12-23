package com.miguan.otk.module.store;

import android.view.ViewGroup;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.list.BaseListFragment;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miguan.otk.adapter.viewholder.OrderViewHolder;
import com.miguan.otk.model.bean.Order;

/**
 * Copyright (c) 2016/12/19. LiaoPeiKun Inc. All rights reserved.
 */
@RequiresPresenter(OrderListPresenter.class)
public class OrderListFragment extends BaseListFragment<OrderListPresenter, Order> {

    @Override
    public BaseViewHolder<Order> createViewHolder(ViewGroup parent, int viewType) {
        return new OrderViewHolder(parent);
    }

}
