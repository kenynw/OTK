package com.miguan.otk.module.user;

import android.view.ViewGroup;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.list.BaseListActivity;
import com.dsk.chain.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miguan.otk.R;
import com.miguan.otk.adapter.viewholder.AreaViewHolder;
import com.miguan.otk.model.bean.Area;

/**
 * Copyright (c) 2017/1/19. LiaoPeiKun Inc. All rights reserved.
 */

@RequiresPresenter(AreaPresenter.class)
public class AreaActivity extends BaseListActivity<AreaPresenter> {

    @Override
    public BaseViewHolder<Area> createViewHolder(ViewGroup parent, int viewType) {
        return new AreaViewHolder(parent);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_toolbar_list;
    }

    @Override
    public ListConfig getListConfig() {
        return super.getListConfig().setLoadMoreAble(false).setNoMoreAble(false).setRefreshAble(false);
    }
}
