package com.miguan.otk.module.rank;

import android.view.ViewGroup;

import com.dsk.chain.bijection.RequiresPresenter;
import com.dsk.chain.expansion.list.BaseListFragment;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miguan.otk.adapter.viewholder.RankViewHolder;
import com.miguan.otk.model.bean.User;

/**
 * Copyright (c) 2016/11/24. LiaoPeiKun Inc. All rights reserved.
 */
@RequiresPresenter(RankListPresenter.class)
public class RankListFragment extends BaseListFragment<RankListPresenter, User> {

    @Override
    public BaseViewHolder<User> createViewHolder(ViewGroup parent, int viewType) {
        return new RankViewHolder(parent);
    }

}
