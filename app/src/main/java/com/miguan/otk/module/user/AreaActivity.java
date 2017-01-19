package com.miguan.otk.module.user;

import android.view.ViewGroup;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.list.BaseListActivity;
import com.dsk.chain.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miguan.otk.adapter.viewholder.AreaViewHolder;
import com.miguan.otk.model.bean.Area;

/**
 * Copyright (c) 2017/1/19. LiaoPeiKun Inc. All rights reserved.
 */

@RequiresPresenter(AreaFragmentPresenter.class)
public class AreaActivity extends BaseListActivity<AreaFragmentPresenter> {

    @Override
    public BaseViewHolder<Area> createViewHolder(ViewGroup parent, int viewType) {
        return new AreaViewHolder(parent);
    }

    @Override
    public ListConfig getListConfig() {
        return super.getListConfig().setLoadMoreAble(false).setNoMoreAble(false).setRefreshAble(false);
    }
}
